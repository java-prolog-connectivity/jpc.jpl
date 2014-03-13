package org.jpc.engine.jpl;
 
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import org.jpc.Jpc;
import org.jpc.query.QueryAdapter;
import org.jpc.query.Solution;
import org.jpc.term.Term;
 
/**
 * An adapter to a JPL query
 * @author sergioc
 *
 */
public class MultiThreadedJplQuery extends QueryAdapter {
 
    /**
     * This class wraps a JPL query in such a way that it does not have the JPL limitations regarding multithreading support.
     * The executor below allows to provide a JPC query (encapsulating a JPL query) that does not have the constraint limiting certain cursor operations to happen in the same thread (as in JPL).
     * This is done executing relevant JPL operations in the context of a single threaded executor (an executor backed up with only one thread).
     * Although we gain on simplifying the usage contract of JPL Query objects (specially in heavy multi-threaded scenarios), 
     * a disadvantage of this approach is that it implies to maintain one separate thread per each JPC query wrapping a JPL query.
     * Although the thread is destroyed when the query is closed, this could imply a certain additional demand of resources (threads) that does not occur in plain JPL.
     * This implementation should be modified (i.e., the executor removed) if JPL eventually removes existing constraints regarding cursor operations occurring in the same/different thread.
     */
    private ExecutorService executor;
     
    public MultiThreadedJplQuery(JplEngine prologEngine, Term goal, boolean errorHandledQuery, Jpc context) {
    	super(new SingleThreadedJplQuery(prologEngine, goal, errorHandledQuery, context));
    }
 
    private ExecutorService getExecutor() {
        if(executor == null)
            executor = Executors.newSingleThreadExecutor(new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread t = new Thread(r, "JPL thread");
                    t.setDaemon(true);
                    return t;
                }
            });
        return executor;
    }

    /**
     * Releases the executor.
     * The current version just shut it down. A more advanced implementation should use an executor pool.
     */
    private void releaseExecutor() {
    	executor.shutdownNow();
        executor = null;
    }
    
    
    @Override
    protected void basicClose() {
        executor = getExecutor();
        try {
        	executor.submit(new Runnable() {
			    @Override
			    public void run() {
			    	SingleThreadedJplQuery stQuery = (SingleThreadedJplQuery)query;
			    	try {
			    		stQuery.close();
			    	} finally {
			    		stQuery.resetJplQuery();
			    	}
			    }
			}).get();
        } catch (ExecutionException e) {
        	Throwable cause = e.getCause();
        	if(cause instanceof RuntimeException)
        		throw (RuntimeException)cause;
        	else
        		throw new RuntimeException(cause);
        } catch(InterruptedException e) {
        	throw new RuntimeException(e);
        } finally {
        	releaseExecutor();
        }
    }
 
    @Override
    public Solution basicNext() {
        executor = getExecutor();
        try {
            return executor.submit(new Callable<Solution>() {
                @Override
                public Solution call() throws Exception {
                	SingleThreadedJplQuery stQuery = (SingleThreadedJplQuery)query;
                	try {
                        return stQuery.next();
                	} catch(Exception e) {
                		stQuery.resetJplQuery();
                		throw e;
                	}
                }
            }).get();
        } catch (ExecutionException e) {
        	releaseExecutor();
        	Throwable cause = e.getCause();
        	if(cause instanceof RuntimeException) {
        		throw (RuntimeException)cause;
        	} else {
        		throw new RuntimeException(cause);
        	}
        } catch(InterruptedException e) {
        	throw new RuntimeException(e);
        }
    }
	
}

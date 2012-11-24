package org.jpc.visitor.jpl;

public abstract class JplStreamingVisitor {
	
	public void visit(jpl.Term term) {
		if(term.isInteger())
			visitInteger((jpl.Integer) term);
		else if (term.isFloat())
			visitFloat((jpl.Float) term);
		else if (term.isVariable())
			visitVariable((jpl.Variable) term);
		else if(term.isAtom())
			visitAtom((jpl.Atom) term);
		else if(term.isCompound()) {
			visitCompound();
			visitCompoundName();
			visit(new jpl.Atom(term.name()));
			endVisitCompoundName();
			for(jpl.Term child : term.args()) {
				visitCompoundArg();
				visit(child);
				endVisitCompoundArg();
			}
			endVisitCompound();
		}
	}
	
	public abstract void visitInteger(jpl.Integer integerTerm);
	public abstract void visitFloat(jpl.Float floatTerm);
	public abstract void visitVariable(jpl.Variable variable);
	public abstract void visitAtom(jpl.Atom atom);
	
	public abstract void visitCompound();
	public abstract void visitCompoundName();
	public abstract void endVisitCompoundName();
	public abstract void visitCompoundArg();
	public abstract void endVisitCompoundArg();
	public abstract void endVisitCompound();
	
}

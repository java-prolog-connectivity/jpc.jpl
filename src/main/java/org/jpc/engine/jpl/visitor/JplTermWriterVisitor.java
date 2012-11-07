package org.jpc.engine.jpl.visitor;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import jpl.Atom;
import jpl.Float;
import jpl.Integer;
import jpl.Variable;

import org.jpc.engine.jpl.JplTermBuilder;
import org.jpc.util.TermBuilder;

public class JplTermWriterVisitor extends JplStreamingVisitor {

	Deque<TermBuilder<jpl.Term>> queue = new LinkedList<>();
	
	public List<jpl.Term> terms() {
		return TermBuilder.asTerms(queue.descendingIterator());
	}
	
	@Override
	public void visitInteger(Integer integerTerm) {
		queue.push(new JplTermBuilder(integerTerm));
	}

	@Override
	public void visitFloat(Float floatTerm) {
		queue.push(new JplTermBuilder(floatTerm));
	}

	@Override
	public void visitVariable(Variable variable) {
		queue.push(new JplTermBuilder(variable));
	}

	@Override
	public void visitAtom(Atom atom) {
		queue.push(new JplTermBuilder(atom));
	}

	@Override
	public void visitCompound() {
		queue.push(new JplTermBuilder());
	}

	@Override
	public void endVisitCompound() {
	}
	
	@Override
	public void visitCompoundName() {
		//queue.peek().setFunctor(name);
	}

	@Override
	public void endVisitCompoundName() {
		TermBuilder compoundNameBuilder = queue.pop();
		TermBuilder compoundBuilder = queue.peek();
		compoundBuilder.setFunctor(compoundNameBuilder.asTerm());
	}

	@Override
	public void visitCompoundArg() {
	}

	@Override
	public void endVisitCompoundArg() {
		TermBuilder compoundArgBuilder = queue.pop();
		TermBuilder compoundBuilder = queue.peek();
		compoundBuilder.addArg(compoundArgBuilder.asTerm());
	}

}

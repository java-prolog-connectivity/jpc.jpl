package org.jpc.visitor.jpl;

import org.jpc.term.Atom;
import org.jpc.term.FloatTerm;
import org.jpc.term.IntegerTerm;
import org.jpc.term.Variable;
import org.jpc.visitor.JpcStreamingVisitor;




public class JpcToJplAdapterVisitor implements JpcStreamingVisitor {

	private JplStreamingVisitor jplVisitor;
	
	public JpcToJplAdapterVisitor(JplStreamingVisitor jplVisitor) {
		this.jplVisitor = jplVisitor;
	}

	@Override
	public void visitInteger(IntegerTerm integerTerm) {
		jplVisitor.visitInteger(new jpl.Integer(integerTerm.longValue()));
	}

	@Override
	public void visitFloat(FloatTerm floatTerm) {
		jplVisitor.visitFloat(new jpl.Float(floatTerm.doubleValue()));
	}

	@Override
	public void visitVariable(Variable variable) {
		jplVisitor.visitVariable(new jpl.Variable(variable.name()));
	}

	@Override
	public void visitAtom(Atom atom) {
		jplVisitor.visitAtom(new jpl.Atom(atom.name()));
	}

	@Override
	public void visitCompound() {
		jplVisitor.visitCompound();
	}

	@Override
	public void visitCompoundName() {
		jplVisitor.visitCompoundName();
	}

	@Override
	public void endVisitCompoundName() {
		jplVisitor.endVisitCompoundName();
	}

	@Override
	public void visitCompoundArg() {
		jplVisitor.visitCompoundArg();
	}

	@Override
	public void endVisitCompoundArg() {
		jplVisitor.endVisitCompoundArg();
	}

	@Override
	public void endVisitCompound() {
		jplVisitor.endVisitCompound();
	}
	


/*
	@Override
	protected jpl.Term transformSimpleTerm(Term source) {
		jpl.Term transformed = null;
		if(source.isVariable()) {
			Variable variable = (Variable) source;
			transformed = new jpl.Variable(variable.name());
		} else if(source.isInteger()) {
			IntegerTerm integerTerm = (IntegerTerm) source;
			transformed = new jpl.Integer(integerTerm.longValue());
		}else if(source.isFloat()) {
			FloatTerm floatTerm = (FloatTerm) source;
			transformed = new jpl.Float(floatTerm.doubleValue());
		} else if(source.isAtom()) {
			Atom atom = (Atom) source;
			transformed = new jpl.Atom(atom.name());
		} else
			throw new JPCTransformationException(JPCPreferences.JPC_SHORT_NAME,
					"JPL",
					"The object " + source + " is not a " + JPCPreferences.JPC_SHORT_NAME + " logic term");
		return transformed;
	}
	
	@Override
	protected jpl.Term transformCompoundTerm(Term nameTerm, List<jpl.Term> transformedChildren) {
		jpl.Term transformed = null;
		if(nameTerm.isAtom()) {
			transformed = new jpl.Compound(((Atom)nameTerm).name(), (jpl.Term[]) transformedChildren.toArray(new jpl.Term[]{}));
		} else {
			throw new JPCTransformationException(JPCPreferences.JPC_SHORT_NAME,
				"JPL",
				"JPL only supports atoms as names of compound terms");
		}
		return transformed;
	}
*/



}

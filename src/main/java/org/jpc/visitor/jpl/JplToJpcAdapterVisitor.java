package org.jpc.visitor.jpl;


import org.jpc.term.Atom;
import org.jpc.term.FloatTerm;
import org.jpc.term.IntegerTerm;
import org.jpc.term.Variable;
import org.jpc.visitor.JpcStreamingVisitor;


public class JplToJpcAdapterVisitor extends JplStreamingVisitor {

	private JpcStreamingVisitor jpcVisitor;
	
	public JplToJpcAdapterVisitor(JpcStreamingVisitor jpcVisitor) {
		this.jpcVisitor = jpcVisitor;
	}

	@Override
	public void visitInteger(jpl.Integer integerTerm) {
		jpcVisitor.visitInteger(new IntegerTerm(integerTerm.longValue()));
	}

	@Override
	public void visitFloat(jpl.Float floatTerm) {
		jpcVisitor.visitFloat(new FloatTerm(floatTerm.doubleValue()));
	}

	@Override
	public void visitVariable(jpl.Variable variable) {
		jpcVisitor.visitVariable(new Variable(variable.name()));
	}

	@Override
	public void visitAtom(jpl.Atom atom) {
		jpcVisitor.visitAtom(new Atom(atom.name()));
	}

	@Override
	public void visitCompound() {
		jpcVisitor.visitCompound();
	}

	@Override
	public void visitCompoundName() {
		jpcVisitor.visitCompoundName();
	}

	@Override
	public void endVisitCompoundName() {
		jpcVisitor.endVisitCompoundName();
	}

	@Override
	public void visitCompoundArg() {
		jpcVisitor.visitCompoundArg();
	}
	
	@Override
	public void endVisitCompoundArg() {
		jpcVisitor.endVisitCompoundArg();
	}

	@Override
	public void endVisitCompound() {
		jpcVisitor.endVisitCompound();
	}


	
	
	/*
	@Override
	protected Term doTransform(Object source, List transformedChildren) {
		Class jplClass = source.getClass();
		Term transformed = null;
		if(jplClass.equals(jpl.Variable.class)) {
			jpl.Variable variable = (jpl.Variable) source;
			transformed = new Variable(variable.name());
		} else if(jplClass.equals(jpl.Integer.class)) {
			jpl.Integer integerTerm = (jpl.Integer) source;
			transformed = new IntegerTerm(integerTerm.longValue());
		}else if(jplClass.equals(jpl.Float.class)) {
			jpl.Float floatTerm = (jpl.Float) source;
			transformed = new FloatTerm(floatTerm.doubleValue());
		} else if(jplClass.equals(jpl.Atom.class)) {
			jpl.Atom atom = (jpl.Atom) source;
			transformed = new Atom(atom.name());
		} else if(jplClass.equals(jpl.Compound.class)) {
			jpl.Compound compound = (jpl.Compound) source;
			transformed = new Compound(compound.name(), transformedChildren);
		} else
			throw new JPCTransformationException("JPL",
				JPCPreferences.JPC_SHORT_NAME,
				"The object " + source + " is not a JPL logic term");
		return transformed;
	}
	
	@Override
	protected List getChildren(Object source) {
		if(source.getClass().equals(jpl.Compound.class)) {
			jpl.Compound compound = (jpl.Compound) source;
			return Arrays.asList(compound.args());
		} else
			return Collections.emptyList();
	}
	*/
}

:- use_module(library(jpl)).
:- op(50, xfx, returns).


:- object(java,
	implements(javap)).

	get_field(Class, Field, Value) :-
		jpl:jpl_get(Class, Field, Value).

	set_field(Class, Field, Value) :-
		jpl:jpl_set(Class, Field, Value).

	method_call(Ref, Method, Params, Result) :-
		jpl:jpl_call(Ref, Method, Params, Result).
	
	new_object(Class, Params, Ref) :- 
		jpl:jpl_new(Class, Params, Ref).
	
:- end_object.



:- object(java(_),
	implements(forwarding, javap)).

	get_field(Class, Field, Value) :-
		jpl:jpl_get(Class, Field, Value).

	set_field(Class, Field, Value) :-
		jpl:jpl_set(Class, Field, Value).

	:- public(returns/2).
	:- public(op(50, xfx, returns)).

	returns(Message, Output) :-
		parameter(1, Ref),
		(	Ref = class(Class) ->
			Handle = Class
		;	jpl:jpl_is_class(Ref) ->
			Handle = Ref
		;	jpl:jpl_is_object(Ref) ->
			Handle = Ref
		;	% assume that we've a class name (an atom)
			jpl:jpl_classname_to_class(Ref, Handle)
		),
		Message =.. [Functor| Arguments],
		writeq(jpl:jpl_call(Handle, Functor, Arguments, Output)), nl,
		jpl:jpl_call(Handle, Functor, Arguments, Output).

/*
	:- public(new/1).
	new(Ref) :- 
		parameter(1, Class),
		jpl:jpl_new(Class, [], Ref).

	:- public(new/2).
	new(Params, Ref) :- 
		parameter(1, Class),
		jpl:jpl_new(Class, Params, Ref).
*/
	forward(Message) :-
		parameter(1, Ref),
		Message =.. [Functor| Arguments],
		jpl:jpl_call(Ref, Functor, Arguments, _).

:- end_object.

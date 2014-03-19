:- object(java,
	implements(javap)).

	get_field(Class, Field, Value) :-
		user::jpl_get(Class, Field, Value).

	set_field(Class, Field, Value) :-
		user::jpl_set(Class, Field, Value).

	method_call(Ref, Method, Params, Result) :-
		user::jpl_call(Ref, Method, Params, Result).
		
:- end_object.

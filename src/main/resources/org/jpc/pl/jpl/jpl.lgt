:- use_module(library(jpl)).


:- object(java(_Reference, _ReturnValue),
	implements(forwarding, javap)).

	:- info([
		version is 1.0,
		author is 'Sergio Castro and Paulo Moura',
		date is 2014/03/25,
		comment is 'Low level API for calling Java from Logtalk using familiar message sending syntax.',
		parnames is ['Reference', 'ReturnValue']
	]).

	:- use_module(jpl, [
		jpl_get/3, jpl_set/3,
		jpl_new/3,
		jpl_call/4
	]).

	:- uses(user, [
		atom_to_term/3
	]).


	get_field(Field, Value) :-
		parameter(1, Class),
		jpl_get(Class, Field, Value).

	set_field(Field, Value) :-
		parameter(1, Class),
		jpl_set(Class, Field, Value).


	check_output_mode(Output) :- var(Output), Output = term(_).
	check_output_mode(Output) :- \+ var(Output).

	set_result(term(JavaResult), JavaResult).
	set_result(serialized(JavaResult), JavaResult).
	%set_result(jref(JavaResult), JavaResult).
	set_result(strong(jref(JavaResult)), JavaResult).
	set_result(weak(jref(JavaResult)), JavaResult).
	set_result(soft(jref(JavaResult)), JavaResult).
	set_result(strong(jref_term(JavaResult)), JavaResult).
	set_result(weak(jref_term(JavaResult)), JavaResult).
	set_result(soft(jref_term(JavaResult)), JavaResult).


	eval(DriverClass, Eval, jref(JavaResult)) :- 
		!,
		jpl_call(DriverClass, 'evalAsObject', [{Eval}], JavaResult),
		jpl_call(DriverClass, 'newWeakJRefTerm', [JavaResult, {JavaResult}], _).
		
	eval(DriverClass, Eval, Output) :- 
		jpl_call(DriverClass, 'evalAsTerm', [{Eval}], {JavaResult}),
		set_result(Output, JavaResult).
		
		
	invoke(Message) :-
		parameter(1, Reference),
		parameter(2, Output),
		check_output_mode(Output),
		LogtalkCall = Reference::Message,
		Eval = eval(LogtalkCall, Output),
		%logtalk::print_message(comment, jpc, calling(jpl_call(class([org,jpc,engine,jpl],['JplDriver']), 'evalAsTerm', [{Eval}], {JavaResult}))),
		DriverClass = class([org,jpc,engine,jpl],['JplDriver']),
		eval(DriverClass, Eval, Output).


	forward(Message) :-
		invoke(Message).


/*
	forget(Ref) :- 
		term_to_atom(Ref, RefAtom), 
		_forget(RefAtom).
*/

/*
	:- multifile(logtalk::message_prefix_stream/4).
	:- dynamic(logtalk::message_prefix_stream/4).

	% Quintus Prolog based prefixes (also used in SICStus Prolog):
	logtalk::message_prefix_stream(comment, jpc, 'INFO ', user_output).
	logtalk::message_prefix_stream(warning, jpc, 'WARNING ', user_output).
	logtalk::message_prefix_stream(error, jpc,   'ERROR ', user_output).

	:- multifile(logtalk::message_tokens//2).
	:- dynamic(logtalk::message_tokens//2).

	% messages for tests handling

	logtalk::message_tokens(calling(Goal), jpc) -->
		[nl, '    CALLING: ~q'-[Goal], nl].
*/

:- end_object.



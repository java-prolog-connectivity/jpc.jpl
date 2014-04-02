
:- initialization((
	%set_logtalk_flag(report, off),
	logtalk_load([jpl, jpc_core(load_driver_required)])
)).
package org.pvcpirates.frc2018.gamepads;

public enum VJoyEnum {
	
	SPACE(1),
	H(2),
	V(3),
	LMENU(4),
	LCONTROL(5),
	LEFTSHIFTKEY(6),
	TAB(7),
	O(8),
	G(9),
	OEMCOMMA(10),
	OEMPERIOD(11),
	VOLUMEMUTE(12),
	VOLUMEDOWN(13),
	VOLUMEUP(14),
	ESCAPE(15);
	
	int val;
	
	VJoyEnum(int val){
		this.val = val;
	}
}

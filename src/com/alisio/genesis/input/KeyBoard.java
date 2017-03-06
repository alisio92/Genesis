package com.alisio.genesis.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoard implements KeyListener {
	
	private boolean[] keys = new boolean[120];
	public boolean up, down, left, right,space,console,debug;
	
	public void update(){
		this.up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		this.down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		this.left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		this.right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];		
		this.space = keys[KeyEvent.VK_SPACE];
		this.console = keys[KeyEvent.VK_T];
		this.debug = keys[KeyEvent.VK_F1];
	}

	public void keyPressed(KeyEvent e) {
		this.keys[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
		this.keys[e.getKeyCode()] = false;
	}


	public void keyTyped(KeyEvent e) {
		
	}
}

package tetris;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput {
	//gets keyboard input
    class ActionListener extends KeyAdapter{
    	
        @Override
    	public void keyPressed(KeyEvent e){
    		int key = e.getKeyCode();
    		
    		switch(genericVariables.get_game_state()) {
    			case "loading":
    				
    				break;
    			case "welcome":
        			if(key == KeyEvent.VK_ENTER) {
        				genericVariables.set_started(true);
        				genericVariables.set_game_state("running");
        			}
        				
    				break;
    			case "paused":
    				if(key == KeyEvent.VK_DOWN) {
    					if(genericVariables.get_pause_selection()+1 < 3)
    						genericVariables.set_pause_selection(genericVariables.get_pause_selection()+1);
    					else
    						genericVariables.set_pause_selection(0);
            		}else if(key == KeyEvent.VK_UP) {
            			if(genericVariables.get_pause_selection() > 0)
    						genericVariables.set_pause_selection(genericVariables.get_pause_selection()-1);
    					else
    						genericVariables.set_pause_selection(2);
            		}
        			if(key == KeyEvent.VK_ENTER)
        				genericVariables.set_pause_apply(true);
    				break;
    			case "end":
    				if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_UP) {
            			if(genericVariables.get_pause_selection() == 0) genericVariables.set_pause_selection(genericVariables.get_pause_selection()+1);
            			else genericVariables.set_pause_selection(genericVariables.get_pause_selection()-1);
            		}
        			if(key == KeyEvent.VK_ENTER)
        				genericVariables.set_pause_apply(true);
    				break;
    			case "running":
	    				//pause game
	            		if(key == KeyEvent.VK_P) {
	            				genericVariables.set_pause(true);
	            				genericVariables.set_game_state("paused");
	            		}
	            		//rotate shape
	            		if(key == KeyEvent.VK_SPACE && genericVariables.get_rotate_available()) {
	            			genericVariables.get_my_tetris().rotate_shape();
	            			genericVariables.set_rotate_available(false);
	            		}
	            		if(key == KeyEvent.VK_DOWN) {
	            			genericVariables.set_down(true);
	            		}
	            		//move shape
	                	if(key == KeyEvent.VK_RIGHT) {
	                		genericVariables.set_left(false);
	                		genericVariables.set_right(true);
	                	}
	                	else if(key == KeyEvent.VK_LEFT) {
	                		genericVariables.set_right(false);
	                		genericVariables.set_left(true);
	                	}
	                	/*else {
	                		genericVariables.set_right(false);
	                		genericVariables.set_left(false);
	                	}*/
    				break;
    			default :
    				System.err.println("!! ERROR : GAME STATE @KeyInput.KeyPressed");
    				break;
    		}
        }
        @Override
    	public void keyReleased(KeyEvent e){
    		int key = e.getKeyCode();
    		
    		switch(genericVariables.get_game_state()) {
    			case "loading":
				
				break;
    			case "welcome":
    				
    				break;
    			case "paused":
    				
    				break;
    			case "end":
    				
    				break;
    			case "running":
    				if(key == KeyEvent.VK_RIGHT && genericVariables.get_right()) {
    	    			genericVariables.set_frameCounter_right(0);
                		genericVariables.set_right(false);
    	    		}
    	    		else if(key == KeyEvent.VK_LEFT && genericVariables.get_left()) {
    	    			genericVariables.set_frameCounter_left(0);
    	    			genericVariables.set_left(false);
    	    		}
            		if(key == KeyEvent.VK_DOWN) {
            			genericVariables.set_down(false);
            			genericVariables.set_speed_down(genericVariables.get_speed_game());
            		}
            		if(key == KeyEvent.VK_SPACE && !genericVariables.get_rotate_available()) {
            			genericVariables.set_rotate_available(true);
            		}
    				break;   
    			default:
    				System.err.println("!! ERROR : GAME STATE @KeyInput.KeyReleased");
    				break;
    		}
    	}
    }
}

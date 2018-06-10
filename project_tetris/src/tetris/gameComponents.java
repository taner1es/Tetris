package tetris;

import java.util.Vector;

//this class is the most important class.
public class gameComponents extends genericVariables{ 
	//declare borders
	public final int top_border = 1*25;
	public final int left_border = 1*25;
	public final int right_border = 21*25;
	public final int bottom_border = 28*25;

	//Game info states
	Vector<shape> all_shapes = new Vector<shape>(0); //this vector keeps the information of the all shapes has drawed and also returning to draw function to draw it for all game time.
	public boolean call_new_shape = true;
	public String game_state = "running"; //game state created to understand for game stopped or running 
	//this method has really really critical role in the program it gets the new shape data and adding to all shapes list (vector) and goes on for everything.
	public void newShape(shape new_shape) {
		all_shapes.addElement(new_shape);
		shape_cnt++;
		gameLog.addElement("New Shape Added to Array list.");
	}
	public void set_call_new_shape (boolean b) {
		call_new_shape = b;
		System.out.println("!! --> size of v_all_shapes : "+ all_shapes.size() + "<-- call_new_shape setted up -->" + Boolean.toString(b));
	}
	public void rotate_shape() {
		int last_index = all_shapes.size()-1;
		int actual_x = all_shapes.lastElement().start_loc_x+100;		//all_shapes.lastElement().loc_x+75;
		int actual_y = all_shapes.lastElement().start_loc_y;		//all_shapes.lastElement().loc_y-50;
		String type = all_shapes.lastElement().shape_type;
		
		int type_no = all_shapes.lastElement().shape_type_no; //shape_codes array first dimension number
		int rot_no = all_shapes.lastElement().shape_rotation_no; // shape_codes array second dimension number
		String code = null;
		if(type_no != 3 ) {
			 // will be taken from array list with new rotation no
			System.out.println("type_no & rot_no : " + type_no + rot_no );
			if(shape_codes[type_no].length-1 == rot_no) {
				rot_no = 0;
				code = shape_codes[type_no][rot_no];
			}else {
				rot_no++;
				code = shape_codes[type_no][rot_no];
			}
			shape rotatedshape;
			rotatedshape = stringtoShape(code,type,type_no,rot_no,actual_x,actual_y);
			rotatedshape.active = false;
			if(checkcollisions(rotatedshape)) {
				rotatedshape.active = true;
				all_shapes.setElementAt(rotatedshape, last_index);
			}
				
			else System.out.println("Collision exists while rotating.");
		}
		else {
			System.out.println("no rotation for this shape.");
		}
		
	}
}

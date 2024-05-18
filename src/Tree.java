package tpe;

public class Tree {

	private TreeNode root;
	
	public Tree() {
		this.root = null;
	}

	
	public void add(Tarea value) {
		if (this.root == null)
			this.root = new TreeNode(value);
		else
			this.add(this.root,value);
	}
	
	public TreeNode getRaiz(){
		return this.root;
	}
	
	private void add(TreeNode actual, Tarea value) {
		if (actual.getValue().getPrioridad() > value.getPrioridad()) {
			if (actual.getLeft() == null) { 
				TreeNode temp = new TreeNode(value);
				actual.setLeft(temp);
			} else {
				add(actual.getLeft(),value);
			}
		} else if (actual.getValue().getPrioridad() < value.getPrioridad()) {
			if (actual.getRight() == null) { 
				TreeNode temp = new TreeNode(value);
				actual.setRight(temp);
			} else {
				add(actual.getRight(),value);
			}
		}
	}
}

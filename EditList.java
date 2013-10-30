import javax.swing.text.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.*;
import javax.swing.undo.*;


//I tried using the UndoManbager class, but ultimately failed in implementing it, because it stated that all of
//the edits it saw were not-undoable (calling canUndo() would never return true). This might possibly be because
//the class was claimed to only be supported up to Java 1.4. This is my own implementation
//based on the description of the UndoManager on Java's oracle site. It keeps a list of
public class EditList implements KeyListener, UndoableEditListener {

	LinkedList<UndoableEdit> edits;
	ListIterator<UndoableEdit> editTracker;
	int editCount = 0;

	public EditList(JTextComponent observed) {
		observed.getDocument().addUndoableEditListener(this);
		edits = new LinkedList<UndoableEdit>();
		editTracker = edits.listIterator();
	}


	public void undoableEditHappened(UndoableEditEvent e) {
		editTracker.add(e.getEdit());
	}

	public boolean canRedo() {
		return editTracker.hasNext();
	}

	public boolean canUndo() {
		return editTracker.hasPrevious();
	}

	public void redo() {
		while(canRedo()) {
			UndoableEdit e = editTracker.next();
			if(e.canRedo()) {
				e.redo();
			}
			if(e.isSignificant()) {
				return;
			}
		}
	}

	public void undo() {
		while(canUndo()) {
			UndoableEdit e = editTracker.previous();
			if(e.canUndo()) {
				e.undo();
			}
			if(e.isSignificant()) {
				return;
			}
		}
	}

	public void keyPressed(KeyEvent k) {

	}
	public void keyReleased(KeyEvent k) {}
	public void keyTyped(KeyEvent k) {}
/*
	private static class TrackableEdit {
		private UndoableEdit e;
		private boolean significant;
		public TrackableEdit(UndoableEdit pE, boolean isSignificant) {
			e = pE;
			significant = isSignificant;
		}

		public boolean redo() {
			if(e.canRedo()) {
				redo();
				return true;
			}
			return false;
		}

		public boolean undo() {
			if(e.canUndo()) {
				undo();
				return true;
			}
			return false;
		}
	}*/
}
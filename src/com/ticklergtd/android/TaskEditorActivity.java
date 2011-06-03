package com.ticklergtd.android;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import com.ticklergtd.android.model.Task;

// TODO: Check if code adheres to Code Style Guidelines as per http://source.android.com/source/code-style.html

public class TaskEditorActivity extends Activity {

	// Dialog identifiers
	private static final int STARTDATE_DIALOG = 1000, DEADLINE_DIALOG = 1001,
			RECURRENCE_DIALOG = 1002;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.task_editor);

		// Testing intent extras.
		// TODO: REMOVE THIS!
		Toast toast = Toast.makeText(
				getApplicationContext(),
				Integer.toString(getIntent().getIntExtra("TASK_EDITOR_EXTRA",
						66)), Toast.LENGTH_SHORT);
		toast.show();		
		
		loadControls();
	}

	private void loadControls() {
		//AutoCompleteTextView task name control 
		AutoCompleteTextView actTaskName = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView_task_name);

		ArrayList<Task> alTask = Task.getTasks(this);
		String sTask[] = new String[alTask.size()];

		for (int i = 0; i < alTask.size(); i++) {
			sTask[i] = alTask.get(i).getName();
		}

		ArrayAdapter<String> aaTask = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, sTask);
		actTaskName.setAdapter(aaTask);
		
		//MultiAutoCompleteTextView context name control		
		MultiAutoCompleteTextView mactControl = (MultiAutoCompleteTextView) findViewById(R.id.multiAutoCompleteTextView_contexts);
		
		ArrayList<Task> alContext = Task.getContext(this);
		String sContext[] = new String[alContext.size()];
		
		ArrayAdapter<String> aaContext = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, sContext);
		mactControl.setAdapter(aaContext);

		mactControl.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
	}	

	// Event handlers for optional settings buttons OnClick method, defined in
	// task_editor.xml

	public void startDateOptionsListener(View v) {
		callStartDateOptionsDialog();
	}

	public void deadlineOptionsListener(View v) {
		callDeadlineOptionsDialog();
	}

	public void recurrenceOptionsListener(View v) {
		callRecurrenceOptionsDialog();
	}
	
	public void taskEditorDoneListener(View v) {
		//TODO  Save data on db		
		Toast toast = Toast.makeText(
				getApplicationContext(),"Not implemented yet.", Toast.LENGTH_SHORT);
		toast.show();
	}
	
	// Calls to optional settings dialogues.
	// TODO: Dialog initialization data should be passed into the extras Bundle.

	private void callStartDateOptionsDialog() {
		Intent intent = new Intent(this, TaskEditorStartDateDialog.class);
		startActivityForResult(intent, STARTDATE_DIALOG);
	}

	private void callDeadlineOptionsDialog() {
		Intent intent = new Intent(this, TaskEditorDeadlineDialog.class);
		startActivityForResult(intent, DEADLINE_DIALOG);
	}

	private void callRecurrenceOptionsDialog() {
		Intent intent = new Intent(this, TaskEditorRecurrenceDialog.class);
		startActivityForResult(intent, RECURRENCE_DIALOG);
	}
}

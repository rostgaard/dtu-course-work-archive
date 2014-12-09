package com.example.prototypemoniterapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.datatypes.EventType;


public class ManageDeviceDialog extends DialogFragment {
	private String[] choices;
	private String macAddress;
	
	
	public static ManageDeviceDialog newInstance(String[] choices, String macAddress) {
		ManageDeviceDialog mdd = new ManageDeviceDialog();
		Bundle args = new Bundle();
		args.putStringArray("choices", choices);
		args.putString("macAddress", macAddress);
		mdd.setArguments(args);
		return mdd;
	}


	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		this.choices = getArguments().getStringArray("choices");
		this.macAddress = getArguments().getString("macAddress");
		
		if (choices.length == 0 || choices[0] == null) {
			choices = new String[1];
			choices[0] = "No actions available";
		}
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(R.string.manage_device_title)
				.setNegativeButton("Cancel", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// No action - Closes dialog
				}
			})
			.setItems(choices, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					final String choice = choices[which];
					Toast.makeText(getActivity(), choice, Toast.LENGTH_LONG).show();
					new Thread(new Runnable() {
						@Override
						public void run() {
							try {
								switch (choice) {
								case "Activate Flashlight":
									WebServiceConnection.invokeAddEventWebServer(macAddress, 0, EventType.FLASHLIGHT);
									break;
								case "Activate Sound":
									WebServiceConnection.invokeAddEventWebServer(macAddress, 0, EventType.PLAYSOUND);
									break;
								case "Get Video Feed":
									Intent videoIntent = new Intent(getActivity(), VideoActivity.class);
									videoIntent.putExtra("MAC", macAddress);
									startActivity(videoIntent);
									break;
								default:
									break;
								}
							} catch (Exception e) {
								// TODO: handle exception
							}							
						}
					}).start();
				}
			});
		return builder.create();
	}	
}

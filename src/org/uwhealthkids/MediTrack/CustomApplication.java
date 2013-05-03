package org.uwhealthkids.MediTrack;

import com.parse.ParseObject;

import android.app.Application;

public class CustomApplication extends Application {
	private static CustomApplication instance;
    private ParseObject currBaby;
    private ParseObject currUser;
    
	public static CustomApplication getInstance() {
        return instance;
    }

    public void onCreate() {
        instance = this;
    }

    public ParseObject getCurrBaby() {
        return this.currBaby;
    }

    public void setCurrBaby(ParseObject newBaby) {
        this.currBaby = newBaby;
    }
    
    public ParseObject getCurrUser() {
    	return this.currUser;
    }
    
    public void setCurrUser(ParseObject newUser) {
    	this.currUser = newUser;
    }
    
}

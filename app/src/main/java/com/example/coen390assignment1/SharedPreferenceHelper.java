package com.example.coen390assignment1;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceHelper {
    private SharedPreferences sharedPreferences;
    public  SharedPreferenceHelper(Context context){
        sharedPreferences = context.getSharedPreferences("EventPreference", Context.MODE_PRIVATE);
    }

    public void saveMaxCount(String max){
        SharedPreferences.Editor editor = sharedPreferences.edit();

        assert editor != null;
        // TODO: Bug check if number then attempt to cast, causes crash
        editor.putInt("maxCount", Integer.parseInt(max));
        editor.commit();
    }

    public void saveEventName(String name, int eventId){
        SharedPreferences.Editor editor = sharedPreferences.edit();

        assert editor != null;
        editor.putString(getEventType(eventId), name);
        editor.commit();
    }

    public String getEventName(int eventId){
        return sharedPreferences.getString(getEventType(eventId), null);
    }

    public String getEventType(int eventId){
        String eventName = null;
        switch (eventId){
            case 1:
                eventName = "eventOne";
                break;
            case 2:
                eventName = "eventTwo";
                break;
            case 3:
                eventName = "eventThree";
                break;
        }
        assert eventName != null;
        return eventName;
    }
}

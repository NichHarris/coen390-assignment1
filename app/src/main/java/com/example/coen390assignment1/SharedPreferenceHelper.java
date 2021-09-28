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
        int maxInt = Integer.parseInt(max);
        assert editor != null;
        if (maxInt > 200){
            editor.putInt("maxCount", 200);
        }
        else if(maxInt < 5){
            editor.putInt("maxCount", 5);
        }
        else{
            editor.putInt("maxCount", maxInt);
        }
        editor.apply();
    }

    public int getMaxCount() {
        return sharedPreferences.getInt("maxCount", 5);
    }

    public void saveTotalEvents(int value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        assert editor != null;
        editor.putInt("totalEvents", value);
        editor.apply();
    }

    public int getTotalEvents(){
        return sharedPreferences.getInt("totalEvents", 0);
    }

    public void saveEventName(String name, int eventId){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        assert editor != null;
        editor.putString(getEventType(eventId), name);
        editor.apply();
    }

    public String getEventName(int eventId){ return sharedPreferences.getString(getEventType(eventId), null);
    }

    public void saveEventValue(int value, int eventId){
        SharedPreferences.Editor editor = sharedPreferences.edit();

        assert editor != null;
        switch (eventId){
            case 0:
                editor.putInt("event1", value);
                break;
            case 1:
                editor.putInt("event2", value);
                break;
            case 2:
                editor.putInt("event3", value);
                break;
        }
        editor.apply();
    }

    public int getEventValue(String eventId){
        return sharedPreferences.getInt(eventId, 0);
    }

    public String getEventType(int eventId){
        String eventName = null;
        switch (eventId){
            case 0:
                eventName = "eventOne";
                break;
            case 1:
                eventName = "eventTwo";
                break;
            case 2:
                eventName = "eventThree";
                break;
        }
        assert eventName != null;
        return eventName;
    }

    // Remove a key/value pair from the SharedPreference
    public void removeData(String key) {
        if(sharedPreferences.contains(key)){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            assert editor != null;
            editor.remove(key);
            editor.apply();
        }
        else{
            return;
        }
    }
}

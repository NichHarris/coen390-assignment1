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

    public int incrementTotalEvents(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int value = sharedPreferences.getInt("totalEvents", 0);
        value++;

        assert editor != null;
        editor.putInt("totalEvents", value);
        editor.apply();
        return value;
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

    public String getEventName(int eventId){ return sharedPreferences.getString(getEventType(eventId), null); }

    public void saveEventValue(int value, int eventId){
        SharedPreferences.Editor editor = sharedPreferences.edit();

        assert editor != null;
        editor.putInt(getEventCounterName(eventId), value);
        editor.apply();
    }

    public int incrementEventValue(int eventId) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String eventName = getEventCounterName(eventId);
        int value = sharedPreferences.getInt(eventName, 0 );
        value++;

        assert editor != null;
        editor.putInt(eventName, value);
        editor.apply();
        return  value;
    }

    public int getEventValue(int eventId){
        return sharedPreferences.getInt(getEventCounterName(eventId), 0);
    }

    public String getEventCounterName(int eventId){
        String eventCounterName = null;
        switch (eventId) {
            case 0:
                eventCounterName = "event1";
                break;
            case 1:
                eventCounterName = "event2";
                break;
            case 2:
                eventCounterName = "event3";
                break;
        }
        assert eventCounterName != null;
        return  eventCounterName;
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

    public void setEditMode(boolean enabled){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        assert editor != null;
        editor.putBoolean("editMode", enabled);
        editor.apply();
    }

    public boolean getEditMode() { return sharedPreferences.getBoolean("editMode", true); }

    public void setDataActivityMode(boolean enabled){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        assert editor != null;
        editor.putBoolean("dataActivityMode", enabled);
        editor.apply();
    }

    public boolean getDataActivityMode() { return sharedPreferences.getBoolean("dataActivityMode", false); }
}

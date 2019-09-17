/*
 * Copyright (C) 2019 SoryApps
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package es.soryapps.securesharedpreferencestest;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Set;

import es.soryapps.securesharedpreferences.SecureSharedPreferences;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TEST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences preferences;
        SharedPreferences.Editor editor;
        Set<String> testSet;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = (SharedPreferences) new SecureSharedPreferences(this, "test");

        editor = preferences.edit();
        editor.putString("stringKey", "stringValue");
        editor.putBoolean("booleanKey", true);
        editor.putFloat("floatKey", 1f);
        editor.putInt("intKey", 1);
        editor.putLong("longKey", 1L);
        testSet = new HashSet<>();
        testSet.add("setValue");
        editor.putStringSet("setKey", testSet);
        editor.apply();

        Log.e(TAG, "Get String: " + preferences.getString("stringKey", "default"));
        Log.e(TAG, "Get Boolean: " + preferences.getBoolean("booleanKey", false));
        Log.e(TAG, "Get Float: " + preferences.getFloat("floatKey", -1f));
        Log.e(TAG, "Get Int: " + preferences.getInt("intKey", -1));
        Log.e(TAG, "Get Long: " + preferences.getLong("longKey", -1L));
        testSet = preferences.getStringSet("setKey", null);
        Log.e(TAG, "Get Set size: " + (testSet != null ? testSet.size() : -1) );

        Log.e(TAG, "Contains stringKey: " + preferences.contains("stringKey"));
        editor = preferences.edit();
        editor.remove("stringKey");
        editor.apply();
        Log.e(TAG, "Contains stringKey: " + preferences.contains("stringKey"));
        Log.e(TAG, "Contains booleanKey: " + preferences.contains("booleanKey"));
        editor = preferences.edit();
        editor.clear();
        editor.apply();
        Log.e(TAG, "Contains booleanKey: " + preferences.contains("booleanKey"));
    }
}

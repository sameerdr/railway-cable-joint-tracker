package com.railway.cablejoint.utils;

import android.content.Context;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.AppendValuesResponse;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.railway.cablejoint.Constants;
import com.railway.cablejoint.models.JointData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SheetsHelper {
    private Sheets sheetsService;
    private Context context;

    public SheetsHelper(Context context, GoogleAccountCredential credential) {
        this.context = context;
        this.sheetsService = new Sheets.Builder(
                new NetHttpTransport(),
                JacksonFactory.getDefaultInstance(),
                credential)
                .setApplicationName("Railway Cable Joint Tracker")
                .build();
    }

    public static GoogleAccountCredential getCredential(Context context, String accountName) {
        GoogleAccountCredential credential = GoogleAccountCredential.usingOAuth2(
                context, Collections.singleton(SheetsScopes.SPREADSHEETS));
        credential.setSelectedAccountName(accountName);
        return credential;
    }

    public void appendData(JointData data) throws Exception {
        List<Object> row = Arrays.asList(
                data.getDate(),
                data.getSection(),
                data.getSubSection(),
                data.getKm(),
                data.getOheMast(),
                data.getOffsetFromTrack(),
                data.getCableType(),
                data.getJointType(),
                data.getPhotoUrl(),
                String.valueOf(data.getLatitude()),
                String.valueOf(data.getLongitude()),
                data.getReason(),
                data.getStaffPresent(),
                data.getRemark()
        );

        ValueRange body = new ValueRange().setValues(Collections.singletonList(row));
        
        AppendValuesResponse result = sheetsService.spreadsheets().values()
                .append(Constants.GOOGLE_SHEET_ID, Constants.SHEET_NAME + "!" + Constants.RANGE, body)
                .setValueInputOption("RAW")
                .execute();
    }

    public List<JointData> getAllData() throws Exception {
        ValueRange response = sheetsService.spreadsheets().values()
                .get(Constants.GOOGLE_SHEET_ID, Constants.SHEET_NAME + "!" + Constants.RANGE)
                .execute();

        List<List<Object>> values = response.getValues();
        List<JointData> dataList = new ArrayList<>();

        if (values != null && values.size() > 1) {
            for (int i = 1; i < values.size(); i++) {
                List<Object> row = values.get(i);
                if (row.size() > 0) {
                    JointData data = new JointData();
                    data.setRowIndex(i + 1);
                    data.setDate(getStringValue(row, Constants.COL_DATE));
                    data.setSection(getStringValue(row, Constants.COL_SECTION));
                    data.setSubSection(getStringValue(row, Constants.COL_SUB_SECTION));
                    data.setKm(getStringValue(row, Constants.COL_KM));
                    data.setOheMast(getStringValue(row, Constants.COL_OHE_MAST));
                    data.setOffsetFromTrack(getStringValue(row, Constants.COL_OFFSET));
                    data.setCableType(getStringValue(row, Constants.COL_CABLE_TYPE));
                    data.setJointType(getStringValue(row, Constants.COL_JOINT_TYPE));
                    data.setPhotoUrl(getStringValue(row, Constants.COL_PHOTO));
                    data.setLatitude(getDoubleValue(row, Constants.COL_LATITUDE));
                    data.setLongitude(getDoubleValue(row, Constants.COL_LONGITUDE));
                    data.setReason(getStringValue(row, Constants.COL_REASON));
                    data.setStaffPresent(getStringValue(row, Constants.COL_STAFF));
                    data.setRemark(getStringValue(row, Constants.COL_REMARK));
                    dataList.add(data);
                }
            }
        }
        return dataList;
    }

    public void updateData(JointData data) throws Exception {
        List<Object> row = Arrays.asList(
                data.getDate(),
                data.getSection(),
                data.getSubSection(),
                data.getKm(),
                data.getOheMast(),
                data.getOffsetFromTrack(),
                data.getCableType(),
                data.getJointType(),
                data.getPhotoUrl(),
                String.valueOf(data.getLatitude()),
                String.valueOf(data.getLongitude()),
                data.getReason(),
                data.getStaffPresent(),
                data.getRemark()
        );

        ValueRange body = new ValueRange().setValues(Collections.singletonList(row));
        String range = Constants.SHEET_NAME + "!A" + data.getRowIndex() + ":N" + data.getRowIndex();

        UpdateValuesResponse result = sheetsService.spreadsheets().values()
                .update(Constants.GOOGLE_SHEET_ID, range, body)
                .setValueInputOption("RAW")
                .execute();
    }

    private String getStringValue(List<Object> row, int index) {
        return row.size() > index ? row.get(index).toString() : "";
    }

    private double getDoubleValue(List<Object> row, int index) {
        try {
            return row.size() > index ? Double.parseDouble(row.get(index).toString()) : 0.0;
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}

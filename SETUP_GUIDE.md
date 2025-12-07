# Railway Cable Joint Tracker - Setup Guide

## 📋 Overview
Complete Android app for tracking railway cable joint locations with Google Sheets backend.

## 🎯 Features Implemented

### 1. Add Data Form ✅
- Date picker
- Section & Sub-Section fields
- KM, OHE Mast, Offset from track
- Cable type & Joint type
- Photo capture/selection
- **Automatic geo-location capture**
- Reason, Staff present, Remark fields

### 2. Show Joint Info ✅
- Display all previous entries in RecyclerView
- Tap to view/edit any entry
- Edit functionality with update to Google Sheets

### 3. Show Map ✅
- Display all joint locations on Google Maps
- Markers with detailed info (Section, KM, Cable type, etc.)
- Auto-zoom to fit all markers

### 4. Reports ✅
- Sort by Section, Sub-Section, or Date
- Export to **Excel (.xlsx)**
- Export to **Word (.docx)**
- Export to **PDF**

## 🔧 Setup Instructions

### Step 1: Google Sheets Setup
Your Google Sheet is already created:
- **Sheet ID**: `1hjViC-yDssz7KPelBYr-2AEDQXm7BU08VG1GyFkB--w`
- **URL**: https://docs.google.com/spreadsheets/d/1hjViC-yDssz7KPelBYr-2AEDQXm7BU08VG1GyFkB--w/edit

### Step 2: Google Cloud Console Setup

1. Go to [Google Cloud Console](https://console.cloud.google.com/)
2. Create a new project or select existing
3. Enable these APIs:
   - Google Sheets API
   - Google Maps SDK for Android
   - Google Drive API

4. **Create OAuth 2.0 Credentials**:
   - Go to Credentials → Create Credentials → OAuth client ID
   - Application type: Android
   - Get SHA-1 fingerprint:
     ```bash
     keytool -list -v -keystore ~/.android/debug.keystore -alias androiddebugkey -storepass android -keypass android
     ```
   - Package name: `com.railway.cablejoint`
   - Download `google-services.json`

5. **Create Google Maps API Key**:
   - Go to Credentials → Create Credentials → API key
   - Restrict to Android apps
   - Add package name and SHA-1

### Step 3: Android Studio Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/sameerdr/railway-cable-joint-tracker.git
   cd railway-cable-joint-tracker
   ```

2. Place `google-services.json` in `app/` directory

3. Update `AndroidManifest.xml`:
   ```xml
   <meta-data
       android:name="com.google.android.geo.API_KEY"
       android:value="YOUR_GOOGLE_MAPS_API_KEY_HERE" />
   ```

4. The Google Sheet ID is already configured in `Constants.java`

### Step 4: Implement Google Sign-In

Add this to your activities where needed:

```java
private GoogleAccountCredential credential;
private static final int REQUEST_ACCOUNT_PICKER = 1000;
private static final int REQUEST_AUTHORIZATION = 1001;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // ... existing code
    
    credential = SheetsHelper.getCredential(this, null);
    chooseAccount();
}

private void chooseAccount() {
    if (credential.getSelectedAccountName() == null) {
        startActivityForResult(
            credential.newChooseAccountIntent(),
            REQUEST_ACCOUNT_PICKER);
    }
}

@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    
    if (requestCode == REQUEST_ACCOUNT_PICKER) {
        if (resultCode == RESULT_OK && data != null) {
            String accountName = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
            if (accountName != null) {
                credential.setSelectedAccountName(accountName);
                // Now you can use the credential
            }
        }
    }
}
```

### Step 5: Permissions

The app requests these permissions:
- `INTERNET` - For Google Sheets API
- `ACCESS_FINE_LOCATION` - For geo-location
- `CAMERA` - For photo capture
- `READ/WRITE_EXTERNAL_STORAGE` - For exports

### Step 6: Build & Run

1. Sync Gradle files
2. Build the project
3. Run on device/emulator (Android 6.0+)

## 📱 App Structure

```
app/
├── src/main/java/com/railway/cablejoint/
│   ├── MainActivity.java              # Main menu
│   ├── AddDataActivity.java           # Add new joint data
│   ├── ShowJointInfoActivity.java     # List all entries
│   ├── EditJointActivity.java         # Edit existing entry
│   ├── ShowMapActivity.java           # Map view
│   ├── ReportActivity.java            # Reports & exports
│   ├── models/
│   │   └── JointData.java             # Data model
│   ├── adapters/
│   │   └── JointDataAdapter.java      # RecyclerView adapter
│   └── utils/
│       ├── SheetsHelper.java          # Google Sheets operations
│       └── ExportHelper.java          # Export to Excel/Word/PDF
└── res/
    └── layout/                         # All XML layouts
```

## 🗄️ Data Structure

Google Sheet columns:
1. Date
2. Section
3. Sub-Section
4. KM
5. OHE Mast
6. Offset from Track
7. Type of Cable
8. Type of Cable Joint
9. Photo URL
10. Latitude (Auto-captured)
11. Longitude (Auto-captured)
12. Reason
13. Staff Present
14. Remark

## 📤 Export Formats

### Excel (.xlsx)
- Full data with all columns
- Auto-sized columns
- Header row with bold formatting

### Word (.docx)
- Professional table format
- Title header
- Key fields included

### PDF
- Clean table layout
- Optimized for printing
- All essential data

## 🔐 Security Notes

1. Keep `google-services.json` secure
2. Don't commit API keys to public repos
3. Use environment variables for sensitive data
4. Implement proper user authentication

## 🐛 Troubleshooting

**Issue**: Google Sheets API not working
- Verify API is enabled in Cloud Console
- Check OAuth credentials are correct
- Ensure account has access to the sheet

**Issue**: Maps not showing
- Verify Maps API key is correct
- Check API is enabled
- Ensure SHA-1 fingerprint matches

**Issue**: Location not captured
- Grant location permissions
- Enable GPS on device
- Check Google Play Services installed

## 📞 Support

For issues or questions:
- GitHub Issues: https://github.com/sameerdr/railway-cable-joint-tracker/issues
- Google Sheet: https://docs.google.com/spreadsheets/d/1hjViC-yDssz7KPelBYr-2AEDQXm7BU08VG1GyFkB--w/edit

## 📄 License

This project is open source and available for railway maintenance teams.

# Quick Reference Guide

## 🎯 Project Links

### GitHub Repository
**URL**: https://github.com/sameerdr/railway-cable-joint-tracker

### Google Sheet (Data Storage)
**Sheet ID**: `1hjViC-yDssz7KPelBYr-2AEDQXm7BU08VG1GyFkB--w`
**Direct Link**: https://docs.google.com/spreadsheets/d/1hjViC-yDssz7KPelBYr-2AEDQXm7BU08VG1GyFkB--w/edit

**Access**: You have owner access to this sheet

## 📋 What's Included

### ✅ Complete Android App
- **4 Main Activities**: Add Data, Show Joint Info, Show Map, Reports
- **Google Sheets Integration**: Real-time data sync
- **Google Maps Integration**: Visual location tracking
- **Export Functionality**: Excel, Word, PDF
- **Auto GPS Capture**: Automatic location recording
- **Photo Support**: Camera/Gallery integration

### 📁 Project Files Created

#### Core Activities (5 files)
1. `MainActivity.java` - Main menu with 4 buttons
2. `AddDataActivity.java` - Form to add new joint data
3. `ShowJointInfoActivity.java` - List all entries
4. `EditJointActivity.java` - Edit existing entries
5. `ShowMapActivity.java` - Google Maps view
6. `ReportActivity.java` - Reports with export

#### Models & Utilities (4 files)
1. `JointData.java` - Data model (Serializable)
2. `SheetsHelper.java` - Google Sheets operations
3. `ExportHelper.java` - Export to Excel/Word/PDF
4. `JointDataAdapter.java` - RecyclerView adapter

#### Layouts (7 XML files)
1. `activity_main.xml` - Main menu layout
2. `activity_add_data.xml` - Add data form
3. `activity_show_joint_info.xml` - List view
4. `activity_edit_joint.xml` - Edit form
5. `activity_show_map.xml` - Map view
6. `activity_report.xml` - Report view
7. `item_joint_data.xml` - List item layout

#### Configuration (8 files)
1. `AndroidManifest.xml` - App configuration
2. `build.gradle` (app) - Dependencies
3. `build.gradle` (root) - Project config
4. `settings.gradle` - Project settings
5. `Constants.java` - App constants
6. `strings.xml` - String resources
7. `themes.xml` - App theme
8. `file_paths.xml` - FileProvider config

#### Documentation (4 files)
1. `README.md` - Project overview
2. `SETUP_GUIDE.md` - Detailed setup instructions
3. `APP_FLOW.md` - Architecture & flow diagrams
4. `QUICK_REFERENCE.md` - This file

**Total**: 28 files created

## 🚀 Quick Setup Checklist

### Step 1: Google Cloud Console
- [ ] Create/Select project
- [ ] Enable Google Sheets API
- [ ] Enable Google Maps SDK for Android
- [ ] Create OAuth 2.0 credentials
- [ ] Download `google-services.json`
- [ ] Create Maps API key

### Step 2: Android Studio
- [ ] Clone repository
- [ ] Place `google-services.json` in `app/` folder
- [ ] Update Maps API key in `AndroidManifest.xml`
- [ ] Sync Gradle files
- [ ] Build project

### Step 3: Test
- [ ] Run on device/emulator
- [ ] Test Add Data form
- [ ] Verify Google Sheets sync
- [ ] Check Map display
- [ ] Test export functions

## 📊 Data Fields

### Required Fields
- ✅ Date (auto-filled with today)
- ✅ Section
- ✅ Geo-Location (auto-captured)

### Optional Fields
- Sub-Section
- KM
- OHE Mast
- Offset from Track
- Type of Cable
- Type of Cable Joint
- Photo
- Reason
- Staff Present
- Remark

## 🔑 Important Constants

### Package Name
```
com.railway.cablejoint
```

### Google Sheet ID
```
1hjViC-yDssz7KPelBYr-2AEDQXm7BU08VG1GyFkB--w
```

### Sheet Name
```
Joint Data
```

### Minimum Android Version
```
API 23 (Android 6.0)
```

## 📱 App Permissions

```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.CAMERA" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```

## 🎨 Color Scheme

- **Primary**: #1976D2 (Blue)
- **Primary Variant**: #1565C0 (Dark Blue)
- **Secondary**: #4CAF50 (Green)
- **Add Button**: #4CAF50 (Green)
- **Info Button**: #2196F3 (Blue)
- **Map Button**: #FF9800 (Orange)
- **Report Button**: #9C27B0 (Purple)

## 📤 Export Locations

All exports saved to:
```
/storage/emulated/0/Download/RailwayJointReports/
```

File naming:
```
joint_report_[timestamp].xlsx
joint_report_[timestamp].docx
joint_report_[timestamp].pdf
```

## 🔧 Key Dependencies

```gradle
// Google Sheets
com.google.api-client:google-api-client-android:2.2.0
com.google.apis:google-api-services-sheets:v4-rev20230227-2.0.0

// Google Maps & Location
com.google.android.gms:play-services-maps:18.2.0
com.google.android.gms:play-services-location:21.1.0

// Export
org.apache.poi:poi-ooxml:5.2.3
com.itextpdf:itext7-core:7.2.5

// Image
com.github.dhaval2404:imagepicker:2.1
```

## 🐛 Common Issues & Solutions

### Issue: Google Sheets API not working
**Solution**: 
1. Verify API is enabled in Cloud Console
2. Check OAuth credentials are correct
3. Ensure `google-services.json` is in `app/` folder

### Issue: Maps not displaying
**Solution**:
1. Verify Maps API key in `AndroidManifest.xml`
2. Check API is enabled in Cloud Console
3. Ensure SHA-1 fingerprint is registered

### Issue: Location not captured
**Solution**:
1. Grant location permissions in app settings
2. Enable GPS on device
3. Check Google Play Services is installed

### Issue: Export not working
**Solution**:
1. Grant storage permissions
2. Check Downloads folder exists
3. Verify Apache POI/iText dependencies

## 📞 Getting Help

### Documentation
- **Setup Guide**: [SETUP_GUIDE.md](SETUP_GUIDE.md)
- **App Flow**: [APP_FLOW.md](APP_FLOW.md)
- **README**: [README.md](README.md)

### Resources
- **GitHub Issues**: https://github.com/sameerdr/railway-cable-joint-tracker/issues
- **Google Sheet**: https://docs.google.com/spreadsheets/d/1hjViC-yDssz7KPelBYr-2AEDQXm7BU08VG1GyFkB--w/edit

## 🎯 Next Steps

1. **Set up Google Cloud Console** (15 mins)
2. **Configure Android Studio** (10 mins)
3. **Build & Test** (5 mins)
4. **Deploy to device** (Ready to use!)

## ✨ Features Checklist

- ✅ Add Data Form with all 14 fields
- ✅ Automatic GPS location capture
- ✅ Photo capture/selection
- ✅ Google Sheets real-time sync
- ✅ View all entries in list
- ✅ Edit any entry
- ✅ Google Maps with markers
- ✅ Sort by Section/Sub-Section/Date
- ✅ Export to Excel (.xlsx)
- ✅ Export to Word (.docx)
- ✅ Export to PDF
- ✅ Material Design UI
- ✅ Offline data caching
- ✅ Auto-zoom map to fit markers

**All features implemented! 🎉**

# Railway Cable Joint Tracker 🚂

Complete Android app for tracking railway cable joint locations with Google Sheets integration.

## ✨ Features

### 1️⃣ Add Data Form
- Date selection with picker
- Section & Sub-Section fields
- KM, OHE Mast, Offset from track
- Cable type & Joint type selection
- Photo capture/upload
- **Automatic geo-location capture** (GPS)
- Reason, Staff present, Remark fields
- Real-time data sync to Google Sheets

### 2️⃣ Show Joint Info
- View all previous entries in scrollable list
- Tap any entry to view full details
- Edit functionality with instant updates
- Beautiful card-based UI

### 3️⃣ Show Map
- Display all joint locations on Google Maps
- Interactive markers with detailed info
- Auto-zoom to fit all locations
- Click markers for Section, KM, Cable details

### 4️⃣ Reports & Export
- **Sort by**: Section, Sub-Section, or Date
- **Export to Excel** (.xlsx) - Full data with formatting
- **Export to Word** (.docx) - Professional table format
- **Export to PDF** - Print-ready reports
- All exports saved to Downloads folder

## 🎯 Google Sheet

Your data is stored in this Google Sheet:
- **Sheet ID**: `1hjViC-yDssz7KPelBYr-2AEDQXm7BU08VG1GyFkB--w`
- **Direct Link**: [Open Google Sheet](https://docs.google.com/spreadsheets/d/1hjViC-yDssz7KPelBYr-2AEDQXm7BU08VG1GyFkB--w/edit)

### Data Structure
| Column | Description |
|--------|-------------|
| Date | Entry date |
| Section | Railway section |
| Sub-Section | Sub-section identifier |
| KM | Kilometer marking |
| OHE Mast | Overhead Equipment mast |
| Offset from Track | Distance from track |
| Type of Cable | Cable specification |
| Type of Cable Joint | Joint specification |
| Photo URL | Image reference |
| Latitude | Auto-captured GPS |
| Longitude | Auto-captured GPS |
| Reason | Work reason |
| Staff Present | Team members |
| Remark | Additional notes |

## 🚀 Quick Start

### Prerequisites
- Android Studio Arctic Fox or later
- Android device/emulator (API 23+)
- Google Cloud account
- Google Maps API key

### Setup Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/sameerdr/railway-cable-joint-tracker.git
   cd railway-cable-joint-tracker
   ```

2. **Google Cloud Setup**
   - Enable Google Sheets API
   - Enable Google Maps SDK for Android
   - Create OAuth 2.0 credentials
   - Download `google-services.json`

3. **Configure the app**
   - Place `google-services.json` in `app/` folder
   - Add Google Maps API key in `AndroidManifest.xml`
   - Sheet ID already configured in `Constants.java`

4. **Build & Run**
   - Open in Android Studio
   - Sync Gradle
   - Run on device

📖 **Detailed setup instructions**: See [SETUP_GUIDE.md](SETUP_GUIDE.md)

## 📱 Screenshots

### Main Menu
4 main options: Add Data, Show Joint Info, Show Map, Reports

### Add Data Form
Complete form with all required fields + automatic location capture

### Joint Info List
Scrollable list of all entries with edit capability

### Map View
Google Maps with markers for all joint locations

### Reports
Sort and export data in multiple formats

## 🛠️ Tech Stack

- **Language**: Java
- **UI**: Material Design Components
- **Backend**: Google Sheets API
- **Maps**: Google Maps SDK
- **Location**: Google Play Services Location
- **Image**: Glide, ImagePicker
- **Export**: Apache POI (Excel/Word), iText (PDF)

## 📦 Dependencies

```gradle
// Google Services
implementation 'com.google.api-client:google-api-client-android:2.2.0'
implementation 'com.google.apis:google-api-services-sheets:v4-rev20230227-2.0.0'
implementation 'com.google.android.gms:play-services-maps:18.2.0'
implementation 'com.google.android.gms:play-services-location:21.1.0'

// Export Libraries
implementation 'org.apache.poi:poi-ooxml:5.2.3'
implementation 'com.itextpdf:itext7-core:7.2.5'
```

## 📂 Project Structure

```
app/src/main/java/com/railway/cablejoint/
├── MainActivity.java              # Main menu
├── AddDataActivity.java           # Add new joint data
├── ShowJointInfoActivity.java     # List all entries
├── EditJointActivity.java         # Edit existing entry
├── ShowMapActivity.java           # Map view with markers
├── ReportActivity.java            # Reports & exports
├── models/
│   └── JointData.java             # Data model
├── adapters/
│   └── JointDataAdapter.java      # RecyclerView adapter
└── utils/
    ├── SheetsHelper.java          # Google Sheets operations
    └── ExportHelper.java          # Export utilities
```

## 🔒 Permissions

- `INTERNET` - Google Sheets API access
- `ACCESS_FINE_LOCATION` - GPS location capture
- `CAMERA` - Photo capture
- `READ/WRITE_EXTERNAL_STORAGE` - Export files

## 🐛 Troubleshooting

**Google Sheets not working?**
- Verify APIs enabled in Cloud Console
- Check OAuth credentials
- Ensure account has sheet access

**Maps not showing?**
- Verify Maps API key
- Check SHA-1 fingerprint
- Ensure API is enabled

**Location not captured?**
- Grant location permissions
- Enable GPS on device
- Check Google Play Services

## 📄 License

Open source - Free for railway maintenance teams

## 🤝 Contributing

Contributions welcome! Please open an issue or submit a PR.

## 📞 Support

- **Issues**: [GitHub Issues](https://github.com/sameerdr/railway-cable-joint-tracker/issues)
- **Google Sheet**: [View Sheet](https://docs.google.com/spreadsheets/d/1hjViC-yDssz7KPelBYr-2AEDQXm7BU08VG1GyFkB--w/edit)

---

**Built for Indian Railways** 🇮🇳

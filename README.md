# BR16GroupAssignment3
This application requires API 26 (oreo) or higher in order to use Integer.parseUnsignedInt found in xmlFileOperations, so you'll need to set up a virtual device using API 26 or higher.

## File Chooser
- To test out the file chooser, add some xml and json files to your virutal device. In Android Studio, once you've connected a device (run your app), go to View > Tool Windows > Device File Explorer. I've been creating a directory and then uploading files all within the Device File Explorer.
- With API 26, user files can be found in storage\self\primary\.
- Currently, the first time the file chooser appears, you need to grant permission for the app to use external storage.
- If you want to mess with any of the file chooser functionality, docs for the library I used can be found at <https://github.com/bartwell/ExFilePicker>.

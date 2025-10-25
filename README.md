# 💬 ChatApp

**ChatApp** is a real-time Android group chat application that enables users to join and chat in groups instantly — without any complex sign-up process.  
The app uses **Firebase Anonymous Authentication** and **Firebase Realtime Database** to provide a fast, secure, and scalable messaging experience.

---

## 🚀 Features

- 🔐 **Anonymous Authentication** – No email or password needed. Users can chat instantly after launching the app.  
- 💬 **Group Chats** – Create and join multiple chat groups to talk with others in real-time.  
- ⚡ **Realtime Messaging** – Messages appear instantly for all users in the group via Firebase Realtime Database.  
- 🧠 **MVVM Architecture** – Clean and organized code structure using ViewModel and Data Binding.  
- 🎨 **Modern UI** – Intuitive design with dialogs, toasts, and responsive layouts.  

---

## 🧩 Tech Stack

- **Language:** Java  
- **Architecture:** MVVM (Model–View–ViewModel)  
- **Backend:** Firebase Realtime Database  
- **Authentication:** Firebase Anonymous Auth  
- **UI:** Android XML with Data Binding  
- **IDE:** Android Studio  

---



## ⚙️ Setup Instructions

1. **Clone this repository**
   ```bash
   git clone https://github.com/sriharigacharya/ChatApp.git
   ```

2. **Open in Android Studio**

3. **Connect to Firebase**
   - Go to **Tools → Firebase → Authentication → Connect to Firebase**
   - Enable **Anonymous Authentication**
   - Set up **Firebase Realtime Database** and choose “Start in test mode” (for development)

4. **Add Firebase Configuration**
   - Download the `google-services.json` file from your Firebase Console  
   - Place it inside the `app/` directory

5. **Run the App**
   - Connect your emulator or physical Android device  
   - Hit **Run ▶️**

---

## 📱 App Flow

1. User launches the app and gets authenticated anonymously.  
2. The app fetches or creates available chat groups.  
3. User can enter any group to view and send messages.  
4. Messages are synced in real time across all connected users.  

---

## 🌱 Future Enhancements

- 📸 Add image and file sharing  
- 🗣️ Add voice messages  
- ✉️ Add message read receipts and typing indicators  
- 👤 Add optional user profiles and display names  
- 🔔 Enable push notifications  

---

## 👨‍💻 Author

**Srihari G Acharya**  
📍 National Institute of Engineering, Mysuru  
💻 CSE (AI & ML) Department  

---

## 📜 License

This project is open-source and available under the **MIT License**.

---

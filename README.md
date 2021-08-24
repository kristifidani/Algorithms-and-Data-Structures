# HMS-Algorithms-and-Data-Structures

## 1. Introduction

- This is a Hotel-Management-System project. This system is going to be responsible for managing a hotel for a single day. Some clients are going to check in, some other clients are going to check out and some rooms need to be cleaned. 
- HotelManagementSystem class needs to implement the provided interface IManagementSystem. 
- I have used Vector as a data structure to store rooms and clients. 
- Additionally, I have used the Graph data structure to organize the wing plan and cleaning procedure.

A hotel consists of rooms, clients and wings.

## 2. Rooms

- There are 2 types of rooms - a double room and a family room. 
- A double room can accommodate 2 people, needs 1 hour to be cleaned. 
- A family room can accommodate 4 people and needs 2 hours to be cleaned. 
- Rooms have an unique ID, room number, wing, status, type and ID of the client that is going to check-in. 
- Family rooms have pririty over double rooms.

## 3. Client check-in/check-out

- Clients have an unique ID, name, email address and ID of the room they're going to check-in.
- If there is a room with status “Ready”, the check in can be completed. The “Ready” room can accommodate the new client and the room’s status changes to “Occupied”. The client doesn’t have to wait.
- If there is not a “Ready” room but there is a “CheckedOut” room available, it can be assigned. In case of assigning a “CheckedOut” room, the new client needs to wait until the cleaning of his room has finished.
- When a client checks-out from a room, the status of that room changes to “CheckedOut” and the room needs to be cleaned before a new client can check in to it.

## 4. Wings and Cleaning Process

- Wings have an unique ID and a name.
- When the organizeCleaning() method is called all the rooms waiting to be cleaned need to be cleaned, respecting their priority. Moreover the rooms need to be cleaned in the most efficient way (following the shortest path between the wings).
- Cleaning starts from the wing with most number of rooms to be cleaned.
- The priority rule: Family rooms have priority over Double rooms.
- All rooms with status “checkedOut” need to be cleaned.

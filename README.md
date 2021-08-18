# HMS-Algorithms-and-Data-Structures

Introduction

In this project you will be implementing a hotel management system. This system is going to be responsible for managing a hotel for a single day. Some clients are going to check in, some other clients are going to check out and some rooms need to be cleaned. 
Hotel Management System class needs to implement the provided interface IManagementSystem.

A hotel consists of rooms, clients and more than one wing.
There are 2 types of rooms - a double room and a family room. A double room can accommodate 2 people, needs 1 hour to be cleaned. A family room can accommodate 4 people and needs 2 hours to be cleaned.
 
When a new client is checking in to the hotel, the following happens:

If there is a room with status “Ready”, the check in can be completed. The “Ready” room can accommodate the new client and the room’s status changes to “Occupied”. The client doesn’t have to wait.
If there is not a “Ready” room but there is a “CheckedOut” room available, it can be assigned. Keep track of rooms that need to be cleaned. In the cleaning process Family rooms have priority over Double rooms, meaning that all Family rooms will need to be cleaned first. In case of assigning a “CheckedOut” room, the new client needs to wait until the cleaning of his room has finished (print this information).
When a client checks out from a room, the status of that room changes to “CheckedOut” and the room needs to be cleaned before a new client can check in to it.

When the organizeCleaning() method is called all the rooms waiting to be cleaned need to be cleaned, respecting their priority. Moreover the rooms need to be cleaned in the most efficient way (following the shortest path between the wings).

The cleaning order of the rooms depends on:

The wing plan of the hotel.
The priority rule: Family rooms have priority over Double rooms.
All rooms with status “checkedOut” need to be cleaned.

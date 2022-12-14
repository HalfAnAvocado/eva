package com.marvin_elsen.eva.uebung_03.aufgabe_02;

// When a socket is connected, receive and send will not perform any security checks on incoming and outgoing
// packets, other than matching the packet's and the socket's address and port. On a send operation, if the packet's
// address is set and the packet's address and the socket's address do not match, an IllegalArgumentException will be
// thrown.

// Connects the socket to a remote address for this socket. When a socket is connected to a remote address, packets
// may only be sent to or received from that address. By default a datagram socket is not connected.


// It's a way to prevent sending or receiving packets to/from other addresses.
public class Main
{
}

package main.java.de.studichat.chatserver.network;


import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public interface Streamable {


    void write(DataOutput out) throws IOException;


    void read(DataInput in) throws IOException;



}

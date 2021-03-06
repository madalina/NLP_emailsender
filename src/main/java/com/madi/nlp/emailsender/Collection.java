package com.madi.nlp.emailsender;

import java.util.HashSet;
import java.util.Set;

public class Collection implements Comparable<Collection>{
    private String name;
    private Set<Note> notes = new HashSet<Note>();
    private int id;
   
    public Collection() {
        
    }
    
    public Collection(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public Collection(String name) {
        this.name = name;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String newName) {
        name = newName;
    }
    
    public Set<Note> getNotes() {
        return notes;
    }
    public void setNotes(Set<Note> notes) {
        this.notes = notes;
    }
    
    public String toString() {
        return name;
    }
    
    public int compareTo(Collection o) {
        if(this.getName() == null || o == null || o.getName() == null)
            return -1;
        return o.getName().compareTo(this.getName());
    }
    public void addNote(Note note) {
        notes.add(note);
    }
}

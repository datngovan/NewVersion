
package com.company;

public interface interfacesOfInteraction {
    public void view(Interaction[] interactions);
    public void delete(Interaction[] interactions, String stringCode);
    public void deleteByLeadId(Interaction[] interactions, String leadCode);
    public void write(Interaction[] interactions);
}
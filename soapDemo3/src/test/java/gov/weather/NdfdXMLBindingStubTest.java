package gov.weather;

import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import java.io.StringReader;

import static org.junit.Assert.*;

public class NdfdXMLBindingStubTest {
    @Test
    public void NDFDgenLatLonList() throws Exception {
        NdfdXMLBindingStub binding = (NdfdXMLBindingStub) new NdfdXMLLocator().getndfdXMLPort();

        String response = binding.latLonListZipCode("53711");
        String latLong = unmarshallResult(response);
        assertEquals("this fake test to returns results to see if service is working", "???", latLong);
    }

    @Test
    public void ActualNDFDgenLatLonListTest() throws Exception {
        NdfdXMLBindingStub binding = (NdfdXMLBindingStub) new NdfdXMLLocator().getndfdXMLPort();

        String response = binding.latLonListZipCode("53711");
        String latLong = unmarshallResult(response);
        assertEquals("Values returned did not match expected", "43.0798,-89.3875", latLong);
    }



    private String unmarshallResult(String response) throws Exception {

        JAXBContext jaxbContext = JAXBContext.newInstance(DwmlType.class);
        try {
            Unmarshaller jaxbUmarshaller = jaxbContext.createUnmarshaller();
            DwmlType dwnl = (DwmlType) jaxbUmarshaller.unmarshal(new StringReader(response));
            return dwnl.getLatLonList();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }

}


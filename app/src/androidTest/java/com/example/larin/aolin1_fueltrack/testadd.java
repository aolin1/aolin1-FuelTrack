package com.example.larin.aolin1_fueltrack;

/**
 * Created by Larin on 2016/2/1.
 */
import android.test.ActivityInstrumentationTestCase2;
public class testadd extends ActivityInstrumentationTestCase2{
    public testadd() {super(MainActivity.class);
    }
    public void testGetLocationFuelEntry() {
        Entrylist Entrylist = new Entrylist("2016-10-10", "Shell", 123.12f, "regular", 11.5f, 43.2f);
        assertTrue(Entrylist.getDate() == "2016-10-10");
    }

    public void teststation() {
        Entrylist Entrylist = new Entrylist("2016-10-10", "Shell", 123.12f, "regular", 11.5f, 43.2f);

        assertTrue(Entrylist.getStation() == "Shell");
    }

    public void testodometer() {

        Entrylist Entrylist = new Entrylist("2016-10-10", "Shell", 123.12f, "regular", 11.5f, 43.2f);

        assertTrue(Entrylist.getOdometer() == 123.12f);
    }

    public void testgrade() {

        Entrylist Entrylist = new Entrylist("2016-10-10", "Shell", 123.12f, "regular", 11.5f, 43.2f);

        assertTrue(Entrylist.getGrade() == "regular");
    }

    public void testGetUnitCostFuelEntry() {

        Entrylist Entrylist = new Entrylist("2016-10-10", "Shell", 123.12f, "regular", 11.5f, 43.2f);

        assertTrue(Entrylist.getCost() == 43.2f);
    }

    public void testGetAmountFuelEntry() {

        Entrylist Entrylist = new Entrylist("2016-10-10", "Shell", 123.12f, "regular", 11.5f, 43.2f);

        assertTrue(Entrylist.getAmount() == 11.5f);
    }


}

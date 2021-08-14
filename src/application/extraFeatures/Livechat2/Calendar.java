package application.extraFeatures.Livechat2;

        import java.awt.*;
        import java.io.*;

        import javax.imageio.ImageIO;
        import javax.swing.*;
        import javax.swing.border.*;

        import com.mindfusion.common.*;
        import com.mindfusion.common.Rectangle;
        import com.mindfusion.drawing.*;
        import com.mindfusion.drawing.awt.AwtImage;
        import com.mindfusion.scheduling.*;
        import com.mindfusion.scheduling.awt.*;
        import com.mindfusion.scheduling.model.*;

public class Calendar extends JFrame {

    AwtCalendar calendar;
    Recurrence recurrence;

    public Calendar() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setMinimumSize(new Dimension(400,600));

        BorderLayout layout = new BorderLayout();
        getContentPane().setLayout(layout);




        // Calendar initialization start
        calendar = new AwtCalendar();
        calendar.beginInit();
        //set the current time
        calendar.setCurrentTime(DateTime.now());
        DateTime today = DateTime.today();
        //set the current date
        calendar.setDate(today);
        // Select the current date
        calendar.getSelection().set(DateTime.today());

        calendar.setCurrentView(CalendarView.SingleMonth);
        calendar.setCustomDraw(CustomDrawElements.CalendarItem);
        calendar.getMonthSettings().getDaySettings().setHeaderSize(20);
        calendar.getItemSettings().setSize(32);
        calendar.endInit();

        calendar.addCalendarListener(new CalendarAdapter()
        {
            @Override()
            public void draw(DrawEvent e) {
                onDraw(e);
            }
        });

        calendar.addCalendarListener(new CalendarAdapter(){
            public void dateClick(ResourceDateEvent e) {
                onDateClicked(e);
            }

        });

        getContentPane().add(calendar, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }


    protected void onDateClicked(ResourceDateEvent e) {

        int dayIndex = e.getDate().getDayOfWeek();

        Appointment item = new Appointment();
        item.setStartTime(e.getDate());
        item.setEndTime(e.getDate());
        item.getStyle().setBrush(brushes[dayIndex]);

        recurrence = new Recurrence();
        recurrence.setPattern(RecurrencePattern.Weekly);
        recurrence.setDaysOfWeek(getDayOfWeek(dayIndex));
        recurrence.setStartDate(e.getDate());
        recurrence.setRecurrenceEnd(RecurrenceEnd.Never);
        item.setRecurrence(recurrence);

        calendar.getSchedule().getItems().add(item);
    }

    private int getDayOfWeek ( int i ) {

        switch (i) {
            case 1:
                return DaysOfWeek.Monday;
            case 2:
                return DaysOfWeek.Tuesday;
            case 3:
                return DaysOfWeek.Wednesday;
            case 4:
                return DaysOfWeek.Thursday;
            case 5:
                return DaysOfWeek.Friday;
            case 6:
                return DaysOfWeek.Saturday;
        }

        return DaysOfWeek.Sunday;

    }


    private void onDraw(DrawEvent e)
    {
        if(recurrence == null)
            return;
        if (e.getElement() == CustomDrawElements.CalendarItem)
        {
            if(e.getDate().getDay() == 6 )
            {
                java.awt.Image img = null;

                /*try {
                    // Read the image file from an input

                    InputStream is = new BufferedInputStream(
                            new FileInputStream("../cake.png"));
                    img = ImageIO.read(is);

                } catch (IOException ioe) {
                }*/

                //gets the bounds of the drawing area
                Rectangle r = e.getBounds();
                AwtImage awtImage = new AwtImage(img);
                //draw the image
                e.getGraphics().drawImage(awtImage, e.getBounds().getLeft(), e.getBounds().getTop(), 32, 32);

            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Calendar window = null;
                try {
                    window = new Calendar();
                    window.setVisible(true);
                }
                catch (Exception exp) {
                }
            }
        });
    }

    Brush[] brushes = {
            Brushes.AliceBlue, Brushes.Beige, Brushes.LightBlue,
            Brushes.LightGreen, Brushes.LightGray, Brushes.LightPink,
            Brushes.LemonChiffon
    };

}


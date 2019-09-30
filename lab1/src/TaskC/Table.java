package TaskC;

import java.util.ArrayList;

public class Table {
    public class Record{
        Record(int id, String author, String title, int year)
        {
            this.id = id;
            this.author = author;
            this.title = title;
            this.year = year;
        }
        int getId()
        {
            return id;
        }
        String getAuthor()
        {
            return author;
        }
        String getTitle()
        {
            return title;
        }
        int getYear()
        {
            return year;
        }
        public void show()
        {
            System.out.println("id: " + id + " | author: " + author + " | title: " + title + " | year: " + year);
        }
        int id;
        String author;
        String title;
        int year;
    }
    enum FIELDS
    {
        ID,
        AUTHOR,
        TITLE,
        YEAR
    };
    private ArrayList<Record> records;
    public void add(int id, String author, String title, int year)
    {
        records.add(new Record(id, author, title, year));
    }
    public void delete(Record record)
    {
        records.remove(record);
    }
    public Record find(FIELDS field, String value)
    {
        switch (field)
        {
            case ID:
            {
                for (Record record: records)
                {
                    int id = Integer.parseInt(value);
                    if (record.getId() == id)
                    {
                        return record;
                    }
                }
                break;
            }
            case TITLE:
            {
                for (Record record: records)
                {
                    if (record.getTitle().equals(value))
                    {
                        return record;
                    }
                }
                break;
            }
            case AUTHOR:
            {
                for (Record record: records)
                {
                    if (record.getAuthor().equals(value))
                    {
                        return record;
                    }
                }
                break;
            }
            case YEAR:
            {
                for (Record record: records)
                {
                    int year = Integer.parseInt(value);
                    if (record.getYear() == year)
                    {
                        return record;
                    }
                }
                break;
            }
        }
        return null;
    }
    public void change(int id, String author, String title, int year)
    {
        for (Record record: records)
        {
            if (record.id == id)
            {
                record.author = author;
                record.title = title;
                record.year = year;
                return;
            }
        }
    }
    public void showAll()
    {
        for (Record record: records)
        {
            record.show();
        }
    }
    private static Table instance;
    private Table()
    {
        records = new ArrayList<Record>();
    }
    public static Table getInstance()
    {
        if (instance == null)
        {
            instance = new Table();
        }
        return instance;
    }
}

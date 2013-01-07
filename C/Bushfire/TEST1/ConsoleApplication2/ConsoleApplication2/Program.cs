using System;
using System.Collections;
using System.Linq;
using System.Text;
using System.Collections.Generic;

namespace ConsoleApplication3
{
    class Program
    {
        static List<Person> people = new List<Person>();
        static List<Item> items = new List<Item>();
        static List<Borrow> borrow = new List<Borrow>();

        static void Main(string[] args)
        {
            Hashtable _array = new Hashtable();
            IDictionaryEnumerator _enum = _array.GetEnumerator();

            Program p = new Program();
            Boolean loopy = true;
            Random rand = new Random();
            int memMin = 1;
            int memMax = 100;
            int itemMin = 101;
            int itemMax = 200;


            people.Add(new Person(rand.Next(memMin,memMax),"Arnold", "Rimmer", "D-Deck", "SmegHead@reddwarf.co.uk"));
            people.Add(new Person(rand.Next(memMin,memMax),"Ford", "Prefect", "Earth", "fordPrefect@h2g2.co.uk"));
            people.Add(new Person(rand.Next(memMin, memMax),"Jack", "Carter", "Bunker,Eureka", "SARAH@globalDynamics.com"));
            people.Add(new Person(rand.Next(memMin, memMax),"Jack", "O'Neill", "CLASSIFIED", "oNeill@chyenneMountain.com"));

            items.Add(new Item(rand.Next(itemMin, itemMax),"18th Centuary Telegraph Poles", "Kryten", "Red Dwarf Press", 3));
            items.Add(new Item(rand.Next(itemMin, itemMax),"The HitchHiker's Guide to the Galaxy", "Various", "MegaDoDO Publications", 1));
            items.Add(new Item(rand.Next(itemMin, itemMax),"Unexplained Phenomina", "US Government", "Eureka Press", 7));
            items.Add(new Item(rand.Next(itemMin, itemMax),"Wormhole Physics made simple", "Samantha Carter", "Air Force", 1));

            while (loopy == true)
            {
                Console.WriteLine("Main Menu");
                Console.WriteLine("----------------------------");
                Console.WriteLine("1) Registration");
                Console.WriteLine("2) Item Management");
                Console.WriteLine("3) Search for Books");
                Console.WriteLine("4) Search for members");
                Console.WriteLine("5) Borrow Items");
                Console.WriteLine("6) Reports");
                Console.WriteLine("7) Exit");

                for (int i = 0; i < borrow.Count(); i++)
                {
                    Console.WriteLine(borrow[i].itemID + " " + borrow[i].member + " " + borrow[i].borrowed + " " + borrow[i].borrowDate + " " + borrow[i].returnDate);
                }

                try
                {
                    string s = Console.ReadLine();
                    int choice = int.Parse(s);

                    switch (choice)
                    {
                        case 1:
                            p.Rego();
                            break;

                        case 2:
                            p.Items();
                            break;

                        case 3:
                            Console.WriteLine("\n");
                            Console.WriteLine("\tSearch for Books");
                            p.BookSearch();
                            break;

                        case 4:
                            Console.WriteLine("\n");
                            Console.WriteLine("\tSearch for members");
                            p.MemberSearch();
                            break;

                        case 5:
                            Console.WriteLine("\n");
                            Console.WriteLine("\tBorrow Items");
                            p.Checkout();
                            break;

                        case 6:
                            Console.WriteLine("\n");
                            Console.WriteLine("\tReports");
                            p.Report();
                            break;

                        case 7:
                            Console.WriteLine("Thank you for using this program");
                            Console.Beep();
                            Environment.Exit(-1);
                            break;
                    }


                }
                catch (Exception e2)
                {
                    Console.WriteLine("ERROR: Incorrect input detected please try again.\n\n");
                }

            }
        }

        public void Rego()
        {
            Boolean regoLoop = true;

            while (regoLoop == true)
            {
                Console.WriteLine("\tPlease Choose One Of The Following:");
                Console.WriteLine("\t--------------------------------------");
                Console.WriteLine("\t1) Add Member");
                Console.WriteLine("\t2) Delete Member");
                Console.WriteLine("\t3) Edit Member");
                Console.WriteLine("\t4) Exit");

                string a = Console.ReadLine();
                int b = int.Parse(a);

                if (b == 1)
                {
                    Add();
                }

                else if (b == 2)
                {
                    Delete();
                }

                else if (b == 3)
                {
                    Edit();
                }

                else if (b == 4)
                {
                    regoLoop = false;
                }

                else
                {
                    Console.WriteLine("ERROR: EXPECTED 1-3. SYSTEM FAIL.\n");
                }
            }


        }

        public void Add()
        {
            string name;
            string surname;
            string email;
            string add;
            Boolean addLoop = true;
            Boolean loop = true;

            while (addLoop != false)
            {
                Console.WriteLine("\nEnter in the first name:");
                name = Console.ReadLine();
                Console.WriteLine("\nEnter in the last name:");
                surname = Console.ReadLine();

                for (int i = 0; i < people.Count(); i++)
                {
                    if (name == people[i].getFirst() && surname == people[i].getLast())
                    {
                        Console.WriteLine("current member already exists");
                        addLoop = false;
                    }
                }

                Console.WriteLine("\nEnter in the address:");
                add = Console.ReadLine();

                while (loop == true)
                {
                    Console.WriteLine("\nEnter in the e-mail:");
                    email = Console.ReadLine();
                    if (checkEmail(email) == true)
                    {
                        Console.WriteLine("New member " + name + " " + surname + " has been added\n\n");
                        loop = false;
                    }
                    else
                    {
                        loop = true;
                    }
                }
                addLoop = false;
                

            }


        }

        public void Delete()
        {
            Console.WriteLine("\nCurrent Members\n");
            Console.WriteLine("Number\t|\tName\t|\tSurname");
            Console.WriteLine("-------------------------------------------------------");
            for (int i = 0; i < people.Count(); i++)
            {
                Console.WriteLine((i + 1) + "\t|\t" + people[i].getFirst() + "\t|\t" + people[i].getLast());
            }

            Console.WriteLine("\nEnter in the number of the victim:");
            string a = Console.ReadLine();
            int b = int.Parse(a);

            if ((b - 1) < people.Count())
            {
                people.Remove(people[b - 1]);
            }
            else
            {

            }

        }


        public void Edit()
        {
            string name;
            string surname;
            string add;
            string email;

            Console.WriteLine("\nCurrent Members\n");
            Console.WriteLine("Number\t|\tName\t|\tSurname");
            Console.WriteLine("-------------------------------------------------------");
            for (int i = 0; i < people.Count(); i++)
            {
                Console.WriteLine((i + 1) + "\t|\t" + people[i].getFirst() + "\t|\t" + people[i].getLast());
            }

            Console.WriteLine("\n\nSelect the member's data you wish to edit: ");
            string a = Console.ReadLine();
            int b = int.Parse(a);

            if ((b - 1) < people.Count())
            {
                int num = b - 1;
                Console.WriteLine("\nMember information: ");
                Console.WriteLine(people[num].getFirst() + "\t| " + people[num].getLast() + "\t| " + people[num].getAdd() + "\t| " + people[num].getEmail());
                Console.WriteLine("What would you like to edit?");
                Console.WriteLine("1) First Name");
                Console.WriteLine("2) Last Name");
                Console.WriteLine("3) Address");
                Console.WriteLine("4) E-mail");
                string n = Console.ReadLine();
                int m = int.Parse(n);
                switch (m)
                {
                    case 1:
                        Console.WriteLine("Current: " + people[num].getFirst());
                        Console.WriteLine("New: ");
                        name = Console.ReadLine();
                        people[num].setFirst(name);
                        Console.WriteLine("\n");
                        break;

                    case 2:
                        Console.WriteLine("Current: " + people[num].getLast());
                        Console.WriteLine("New: ");
                        surname = Console.ReadLine();
                        people[num].setLast(surname);
                        Console.WriteLine("\n");
                        break;

                    case 3:
                        Console.WriteLine("Current: " + people[num].getAdd());
                        Console.WriteLine("New: ");
                        add = Console.ReadLine();
                        people[num].setAdd(add);
                        Console.WriteLine("\n");
                        break;

                    case 4:
                        Console.WriteLine("Current: " + people[num].getEmail());
                        Console.WriteLine("New: ");
                        email = Console.ReadLine();
                        people[num].setEmail(email);
                        Console.WriteLine("\n");
                        break;
                }

            }
            else
            {

            }

        }

        public void Items()
        {
            Boolean regoLoop = true;
            Boolean itemLoop = true;
            string title;
            string author;
            string publish;
            int copy;

            while (regoLoop == true)
            {
                Console.WriteLine("\tPlease Choose One Of The Following:");
                Console.WriteLine("\t--------------------------------------");
                Console.WriteLine("\t1) Add Item");
                Console.WriteLine("\t2) Delete Item");
                Console.WriteLine("\t3) Edit Item");
                Console.WriteLine("\t4) Exit");

                string a = Console.ReadLine();
                int b = int.Parse(a);

                if (b == 1)
                {
                    while (itemLoop != false)
                    {
                        Console.WriteLine("\nEnter in the title:");
                        title = Console.ReadLine();

                        for (int i = 0; i < people.Count(); i++)
                        {
                            if (title == items[i].getTitle())
                            {
                                Console.WriteLine("current item already exists");
                                Console.WriteLine("would you like to add another?");
                                Console.WriteLine("1 - Yes");
                                Console.WriteLine("2 - No");
                                string y = Console.ReadLine().ToLower();
                                int d = int.Parse(y);

                                switch(d)
                                {
                                    case 1:
                                        items[i].setCopy((items[i].getCopy() + 1));
                                        break;

                                    case 2:
                                        break;
                                }
                                itemLoop = false;
                            }
                        }

                        Console.WriteLine("\nEnter in the author:");
                        author = Console.ReadLine();

                        Console.WriteLine("\nEnter in the publisher:");
                        publish = Console.ReadLine();

                        Console.WriteLine("\nEnter in the number of copies:");
                        copy = int.Parse(Console.ReadLine());

                        Console.WriteLine("New item " + title + " by " + author + " has been added\n\n");
                        itemLoop = false;

                    }
                }

                else if (b == 2)
                {
                    Console.WriteLine("\nCurrent Items Stored\n");
                    Console.WriteLine("Number\t|\tTitle\t|\tAuthor\t|\tCopies");
                    Console.WriteLine("-------------------------------------------------------");
                    for (int i = 0; i < items.Count(); i++)
                    {
                        Console.WriteLine((i + 1) + "\t|\t" + items[i].getTitle() + "\t|\t" + items[i].getAuth() + "\t|\t" + items[i].getCopy());
                    }

                    Console.WriteLine("\nEnter in the number of the victim:");
                    string c = Console.ReadLine();
                    int d = int.Parse(c);
                    int e = d - 1;

                    if (e < items.Count())
                    {
                        if (items[e].getCopy() == 1)
                        {
                            items.Remove(items[e]);
                        }
                        else
                        {
                            items[e].setCopy((items[e].getCopy() - 1));
                        }


                    }
                    else
                    {

                    }
                }

                else if (b == 3)
                {
                    Console.WriteLine("\nCurrent Items Stored\n");
                    Console.WriteLine("Title\t|\tAuthor\t|\tCopies");
                    Console.WriteLine("-------------------------------------------------------");
                    for (int i = 0; i < items.Count(); i++)
                    {
                        Console.WriteLine(items[i].getTitle() + "\t|\t" + items[i].getAuth() + "\t|\t" + items[i].getCopy());
                    }

                    Console.WriteLine("\n\nSelect the member's data you wish to edit: ");
                    string c = Console.ReadLine();
                    int d = int.Parse(a);

                    if ((b - 1) < people.Count())
                    {
                        int num = b - 1;
                        Console.WriteLine("\nItem information: ");
                        Console.WriteLine(items[num].getTitle() + "\t|\t" + items[num].getAuth() + "\t|\t" + items[num].getCopy());
                        Console.WriteLine("What would you like to edit?");
                        Console.WriteLine("1) Title");
                        Console.WriteLine("2) Author");
                        Console.WriteLine("3) Publisher");
                        Console.WriteLine("4) Number of Copies");
                        string n = Console.ReadLine();
                        int m = int.Parse(n);
                        switch (m)
                        {
                            case 1:
                                Console.WriteLine("Current Title: " + items[num].getTitle());
                                Console.WriteLine("New: ");
                                title = Console.ReadLine();
                                items[num].setTitle(title);
                                Console.WriteLine("\n");
                                break;

                            case 2:
                                Console.WriteLine("Current: " + items[num].getAuth());
                                Console.WriteLine("New: ");
                                author = Console.ReadLine();
                                people[num].setLast(author);
                                Console.WriteLine("\n");
                                break;

                            case 3:
                                Console.WriteLine("Current: " + items[num].getPub());
                                Console.WriteLine("New: ");
                                publish = Console.ReadLine();
                                items[num].setPub(publish);
                                Console.WriteLine("\n");
                                break;

                            case 4:
                                Console.WriteLine("Current: " + items[num].getCopy());
                                Console.WriteLine("New: ");
                                copy = int.Parse(Console.ReadLine());
                                items[num].setCopy(copy);
                                Console.WriteLine("\n");
                                break;

                        }

                    }
                    else
                    {

                    }
                }

                else if (b == 4)
                {
                    regoLoop = false;
                }

                else
                {
                    Console.WriteLine("ERROR: EXPECTED 1-3. SYSTEM FAIL.\n");
                }
            }
        }

        public void BookSearch()
        {
            string title;
            string author;
            int c=items.Count();
            string[] temp = new string[c];
            ArrayList array = new ArrayList();
            Console.WriteLine("Select one of the following: ");
            Console.WriteLine("1) Search by Title");
            Console.WriteLine("2) Search by Author");

            string a = Console.ReadLine();
            int b = int.Parse(a);

            Console.WriteLine("\n");

            switch (b)
            {
                case 1:
                    Console.WriteLine("Enter in the title of the book you wish to find: ");
                    title = Console.ReadLine();
                    for (int k = 0; k < items.Count(); k++)
                    {
                        temp[k]=items[k].getTitle();
                        
                    }

                    for (int i = 0; i < items.Count(); i++)
                    {
                        if (items[i].getTitle().Contains(title))
                        {
                            array.Add(i);
                        }

                    }
                    break;

                case 2:
                    Console.WriteLine("Enter in the author of the book you wish to find: ");
                    author = Console.ReadLine();
                    for (int i = 0; i < items.Count(); i++)
                    {
                        if (author == items[i].getAuth())
                        {
                            array.Add(i);
                        }
                    }
                    break;
            }
            Console.WriteLine("\n");
            foreach (int j in array)
            {
                Console.WriteLine("ID Number: "+items[j].getID());
                Console.WriteLine("Full Title: "+items[j].getTitle());
                Console.WriteLine("Author: " + " " + items[j].getAuth()); 
                Console.WriteLine("Number Avalible: "+ items[j].getCopy());
                Console.WriteLine("\n");

            }
            array.Clear();
            Console.WriteLine("\n\n");
        }

        public void MemberSearch()
        {
            string surname;
            string email;
            ArrayList array = new ArrayList();
            Console.WriteLine("Select one of the following: ");
            Console.WriteLine("1) Search by Surname");
            Console.WriteLine("2) Search by E-mail Address");
            string a = Console.ReadLine();
            int b = int.Parse(a);
            Console.WriteLine("\n");
            switch (b)
            {
                case 1:
                    Console.WriteLine("Enter in the member's surname: ");
                    surname = Console.ReadLine();
                    for (int i = 0; i < items.Count(); i++)
                    {
                        if (surname == people[i].getLast())
                        {
                            array.Add(i);
                        }
                    }
                    break;

                case 2:
                    Console.WriteLine("Enter in the member's e-mail: ");
                    email = Console.ReadLine();
                    for (int i = 0; i < items.Count(); i++)
                    {
                        if (email == people[i].getEmail())
                        {
                            array.Add(i);
                        }
                    }
                    break;
            }

            foreach (int j in array)
            {
                Console.WriteLine(people[j].getFirst() + " " + people[j].getLast() + " " + people[j].getAdd());

            }
            array.Clear();
            Console.WriteLine("\n\n");
        }

        public void Checkout()
        {
            int name;
            int mem=0;
            int id;
            int no=0;
   
            DateTime day = DateTime.Now;
            
            Console.WriteLine("List of books: ");
            Console.WriteLine(" ID \t|\t Title \t|\t Author \t|\t Avalible");
            for (int i = 0; i < items.Count(); i++)
            {
                Console.WriteLine(items[i].getID() + "\t |\t " + items[i].getTitle() + "\t |\t " + items[i].getAuth() + "\t |\t " + items[i].getCopy());
            }
            Console.WriteLine("Enter in the item's ID: ");
            string a = Console.ReadLine();
            id = int.Parse(a);

            Console.WriteLine("\n\nList of members: ");
            Console.WriteLine(" ID \t|\t First Name \t|\t Last Name \t|\t E-mail");
            for (int i = 0; i < items.Count(); i++)
            {
                Console.WriteLine(people[i].memId + "\t |\t " + people[i].getFirst() + "\t |\t " + people[i].getLast() + "\t |\t " + people[i].getEmail());
            }
            Console.WriteLine("Select the member: ");
            string c = Console.ReadLine();
            name = int.Parse(c);
            for (int i = 0; i < people.Count(); i++)
            {
                if (name == people[i].memId)
                {
                    mem = people[i].memId;
                }
            }

            Console.WriteLine("Enter in the number of copies you want: ");
            string b = Console.ReadLine();
            no = int.Parse(b);


            for (int i = 0; i < items.Count(); i++)
            {
                if (items[i].getID() == id && no != 0 && no<=items[i].getCopy())
                {
                    borrow.Add(new Borrow(mem, id, no, day, day.AddDays(7)));
                    items[i].setCopy(items[i].getCopy()-no);
                    Console.WriteLine("Successfully Borrowed.");
                }
            }
        }

        public void Report()
        {
            int copy=0;

            Console.WriteLine("Select one of the following: ");
            Console.WriteLine("1) Item List");
            Console.WriteLine("2) Member List");
            Console.WriteLine("3) Borrowed Items");

            string a = Console.ReadLine();
            int b = int.Parse(a);

            switch (b)
            {
                case 1:
                    Console.WriteLine("\t\tITEM LIST");
                    Console.WriteLine("I.D. | Book Title\t| Author\t| Publisher\t| IN | OUT |");

                    for (int i = 0; i < items.Count(); i++)
                    {
                        for (int j = 0; j < borrow.Count(); j++)
                        {
                            if (items[i].getID() == borrow[j].itemID)
                            {
                                copy = borrow[j].borrowed;
                            }                            
                        }
                        Console.WriteLine(items[i].getID() + " |  " + items[i].getTitle() + " | " + items[i].getAuth() + " | " + items[i].getPub() + " | " + items[i].getCopy() + " | " + copy);
                        copy = 0;
                    }
                    break;

                case 2:
                    Console.WriteLine("\t\tMEMBER LIST");
                    Console.WriteLine("I.D. | Name \t");

                    for (int i = 0; i < people.Count(); i++)
                    {
                        Console.WriteLine(people[i].memId + " |  " + people[i].getFirst() +" "+ people[i].getLast());
                    }
                    Console.WriteLine("\n\n");
                    break;

                case 3:
                    Console.WriteLine("\t\tBorrowed Items: \n");
                    for(int x=0;x<borrow.Count();x++)
                    {
                        for(int z=0;z<people.Count();z++)
                        {                       
                            if(people[z].memId == borrow[x].member)
                            {
                                for (int i = 0; i < items.Count(); i++)
                                {                                
                                    if (borrow[x].itemID == items[i].getID())
                                    {                                   
                                        Console.WriteLine(people[z].memId + " |  " + people[z].getFirst() + " " + people[z].getLast() + " | " + items[i].getTitle());
                                        Console.WriteLine("Borrowed On: {0:ddd MMM dd, yyyy}   Return Date: {0:ddd MMM dd, yyyy}", borrow[z].borrowDate,borrow[z].returnDate);
                                        Console.WriteLine("\n");
                                    }
                                }                            
                            }
                        }
                    }
                    break;
            }
        }


        public Boolean checkEmail(string email)
		{
            string good = @"^(([\w-]+\.)+[\w-]+|([a-zA-Z]{1}|[\w-]{2,}))@" + @"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\."+ @"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"+ @"([a-zA-Z]+[\w-]+\.)+[a-zA-Z]{2,4})$";

            if (System.Text.RegularExpressions.Regex.IsMatch(email, good))
            {
                return true;
            }
            else
            {

            }

 			return false;
		}


    }

}

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ConsoleApplication3
{
    class Person
    {
        private string first;
        private string last;
        private string add;
        private string email;
        int id;

        public Person(int id,string name, string surname, string address, string email)
        {
            this.first = name;
            this.last = surname;
            this.add = address;
            this.email = email;
            this.id = id;
        }

        public string getFirst()
        {
            return first;
        }
        public string getLast()
        {
            return last;
        }
        public string getAdd()
        {
            return add;
        }
        public string getEmail()
        {
            return email;
        }

        public int memId
        {
            get{return id;}
            set { id = value; }
        }


        public void setFirst(string a)
        {
            first=a;
        }
        public void setLast(string a)
        {
            last=a;
        }
        public void setAdd(string a)
        {
            add=a;
        }
        public void setEmail(string a)
        {
            email=a;
        }
    }
}

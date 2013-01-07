using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Collections;

namespace ConsoleApplication3
{
    class Item
    {
        private string title;
        private string auth;
        private int copy;
        private string pub;
        private int id;

        public Item(int id,string title, string author,string publisher,int copy)
        {
            this.title = title;
            this.auth = author;
            this.copy = copy;
            this.pub = publisher;
            this.id = id;
        }

        public string getTitle()
        {
            return title;
        }
        public string getAuth()
        {
            return auth;
        }
        public int getCopy()
        {
            return copy;
        }
        public string getPub()
        {
            return pub;
        }

        public int getID()
        {
            return id;
        }

        public void setTitle(string a)
        {
            title = a;
        }
        public void setAuth(string a)
        {
            auth = a;
        }
        public void setCopy(int a)
        {
            copy = a;
        }
        public void setPub(string a)
        {
            pub=a;
        }

    }





}

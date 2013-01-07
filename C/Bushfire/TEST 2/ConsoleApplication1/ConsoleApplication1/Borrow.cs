using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ConsoleApplication3
{
    class Borrow
    {
        private int copy;
        private int mem;
        private int item;
        private DateTime start;
        private DateTime end;

        public Borrow(int member,int item,int copy,DateTime startDate,DateTime endDate)
        {
            this.mem = member;
            this.item = item;
            this.copy = copy;
            this.start = startDate;
            this.end = endDate;
        }

        public int member
        {
            get { return mem; }
            set { mem = value; }
        }

        public int itemID
        {
            get { return item; }
            set { item = value;}
        }

        public int borrowed
        {
            get { return copy; }
            set { copy = value; }
        }

        public DateTime borrowDate
        {
            get { return start; }
            set { start = value; }
        }

        public DateTime returnDate
        {
            get{return end;}
            set { end = value; }
        }

    }
}

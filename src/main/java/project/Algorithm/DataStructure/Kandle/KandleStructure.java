/*
 * Copyright (c) 1997, 2011, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

	package src.main.java.project.Algorithm.Datastructure.Kandle;

/**
 * KandleList collection
 * 
 * @author  Cleyson Oliveira
 * @see     List
 * @see     LinkedList
 * @see     BinaryTree
 * @param <E> the type of elements held in this collection
 */


import java.lang.NullPointerException;

public class KandleStructure<E extends Comparable<E>>{

	private static class Kandle<E extends Comparable> implements Comparable<Kandle>{
		Kandle<E> next;
        Kandle<E> prev;
        E smallest;
        E biggest;

		Kandle(){}
        Kandle(E smallest, E biggest) {
            this.smallest = smallest;
            this.biggest = biggest;
            this.prev = this.next = null;
        }
        Kandle(Kandle<E> prev, E smallest, E biggest, Kandle<E> next) {
            this.smallest = smallest;
            this.biggest = biggest;
            this.prev = prev;
            this.next = next;
        }

        /*
         * -2 if is a kandle comparing with a package 
         * -1 smallest != value 
         *  0 if they are equals
         *  1 biggest != value
         *  2 biggest and smallest != value 
         */
        @Override
        public int compareTo(Kandle compare){
		int result = 0;

            if(smallest != compare.smallest)
                result = -1;

            if(biggest != compare.biggest)
                if(result < 0)
                    result = 2;
                else
                    result = 1;

            return result;
		}
	
        @Override
        public String toString(){
        	return "{ [" + smallest.toString() + "] kn [" + biggest.toString() + "] }";
        }

        public String smallest(){
        	return " [" + smallest.toString() + "] ";
        }
		public String biggest(){
        	return " [" + biggest.toString() + "] ";
        }
	}

	private static class KandlePackage extends Kandle{
		Kandle smallest;
		Kandle biggest;
		Kandle prev;
		Kandle next;

		KandlePackage(Kandle smallest, Kandle biggest){
			super();
            this.smallest = smallest;
            this.biggest = biggest;
		}
        KandlePackage(Kandle prev, Kandle smallest, Kandle biggest, Kandle next) {
            super();
            this.smallest = smallest;
            this.biggest = biggest;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString(){
        	return "{" + smallest.smallest() + "pk" + biggest.biggest() +"}";
        }
	}


		/**
		 * Count how many packages the list is carrying
		 */
		private int countPackages = 0;
		
		/**
		 * Count how many Kandles the list is carrying
		 */
		private int countKandles = 0;
	    
	    /**
	     * Pointer to first object on the list.
	     */
	    private Kandle<E> first;

	    /**
	     * Pointer to last object on the list.
	     */
	    private Kandle<E> last;


    /**
     * Constructs an empty list.
     */
	public KandleStructure() {}



	/**
	 * This method will add the new value range
	 * on sorted position in this list;
     * @param <E> s - smallest value to be hold
     * @param <E> b - biggest value to be hold
     */
    public Kandle add(E s, E b){
    	return insert(s, b, first);
    }

	/**
	 * Insert method searchs through the list due
	 * to find the sorted place to put new kandle
	 ** works recursively **
     * @param <E> s - smallest value to be hold
     * @param <E> b - biggest value to be hold
     * @param <Kandle> k - Kandle on the present
     * list to search the right sorted spot to
     * put the new value on;
     */
    private Kandle insert(E s, E b, Kandle k){

    	/* ** Simple Insertion
    	 * This will happen when there is no Kandle on the list or
    	 * when the new range is biggest than the existing list;
    	 */
    	if( k == null ){
    		k = new Kandle(s, b);
    		
    		if(first == null)
    			first = last = k; // = ikp = fkp = k;
    		else
    			linkAfter(k, last);
    		

            if(countKandles >= 4)
                //TODO packKandles();

    		last = k;
            countKandles++;
    		return last;
    	}
			
    		k = new Kandle(s, b);
			linkAfter(k, last);
			last = k;
            countKandles++;
    	return last;
    }

    private void linkAfter(Kandle nk, Kandle k){
        if(nk == null || k == null)
            throw new NullPointerException("Can not link after on a null object");
        if (nk.prev != null) nk.prev.next = null;
        if (k.next != null) k.next.prev = null;
        nk.prev = k;
        k.next = nk;
    }


    public void pack(){
    	Kandle k = new KandlePackage(first, last);
    	first = last = k;
    }

	public void forcePack(){
    	Kandle k = new KandlePackage(first.next, last);
    	linkAfter(k, first);
    	last = k;
    }

    @Override
    public String toString(){
		return allToString(first);
    }

    private String allToString (Kandle k) {
    	if (k == null)
    		return "";
    	return k.toString() + " " + allToString(k.next);
    }
}
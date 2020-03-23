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

import src.main.java.project.Algorithm.Datastructure.ReferenceList.LinkedList;
import src.main.java.project.logger.Logger;
import src.main.resources.Messages;

import java.lang.NullPointerException;
import java.util.ArrayList;

public class KandleStructure<E extends Comparable<E>>{

	//Number of Kandles that would be hold on a package
	private int PACKAGE_SIZING = 4;

	private static class Kandle<E extends Comparable> implements Comparable<Kandle>{
        private static int n = 1; //Kandle Kn*
        final int v = n;

        final int level = 0;
		Kandle<E> next;
        Kandle<E> prev;
        E smallest;
        E biggest;

		Kandle(){}
        Kandle(E smallest, E biggest) {
            this.smallest = smallest;
            this.biggest = biggest;
            this.prev = this.next = null;
            n++;
        }
        Kandle(Kandle<E> prev, E smallest, E biggest, Kandle<E> next) {
            this.smallest = smallest;
            this.biggest = biggest;
            this.prev = prev;
            this.next = next;
            n++;
        }

        public int getLevel(){
            return level;
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
        	return "{ [" + smallest.toString() + "] K" + v +" [" + biggest.toString() + "] }";
        }

        public String smallestToString(){
        	return " [" + smallest.toString() + "] ";
        }
		public String biggestToString(){
        	return " [" + biggest.toString() + "] ";
        }
	}




	private static class KandlePackage extends Kandle{
		Kandle smallest;
		Kandle biggest;
		Kandle prev;
		Kandle next;
        int level;

		KandlePackage(Kandle smallest, Kandle biggest, int level){
			super();
            this.smallest = smallest;
            this.biggest = biggest;
            this.level = level;
		}
        KandlePackage(Kandle prev, Kandle smallest, Kandle biggest, Kandle next, int level) {
            super();
            this.smallest = smallest;
            this.biggest = biggest;
            this.level = level;
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
        public int compareTo(KandlePackage compare){
        int result = 0;

            if(smallestKandle().compareTo(compare.smallestKandle()) == 0 )
                result = -1;

            if(biggestKandle().compareTo(compare.biggestKandle()) == 0 )
                if(result < 0)
                    result = 2;
                else
                    result = 1;

            return result;
        }



        @Override
        public String smallestToString(){
        	Kandle k = smallest;
        	while(k instanceof KandlePackage)
        		k = (KandlePackage) k.smallest;
        	return k.smallestToString();
        }

        @Override
        public String biggestToString(){
            Kandle k = biggest;
            while(k instanceof KandlePackage)
                k = (KandlePackage) k.biggest;
            return k.biggestToString();
        }

        public Kandle smallestKandle(){
            Kandle k = smallest;
            while(k instanceof KandlePackage)
                k = (KandlePackage) k.smallest;
            return k;
        }
        public Kandle biggestKandle(){
            Kandle k = biggest;
            while(k instanceof KandlePackage)
                k = (KandlePackage) k.biggest;
            return k;
        }

        @Override
        public String toString(){
        	return "{" + smallest.smallestToString() + "P"+level+ biggest.biggestToString() +"}";
        }
	}


	    /**
	     * Pointer to first object on the list.
	     */
	    transient Kandle<E> first;

	    /**
	     * Pointer to last object on the list.
	     */
	    transient Kandle<E> last;

        /**
         * Pointer to last object on the list.
         */
        transient ArrayList<LinkedList> references;
        /**
         * log reference
         */
        transient Logger log;

    /**
     * Constructs an empty list.
     */
	public KandleStructure() {
        first = last = null;
        references = new ArrayList(4);
        references.add(new LinkedList());
        log = log.getInstance();
    }



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
    		
    		if(first == null){
    			references.get(0).add(first = last = k);
                log.createNewKandle(s,b,Messages.CREATE_FIRST.toString());
    			return k;
    		} else {
    		    packing();

                references.get(0).add(k);
    			linkAfter(k, last);
                log.createNewKandle(s,b,k.prev.toString(), true);
            }

    		last = k;
    		return k;
    	}

    	return insert(s, b, k.next);
    }


    private int flag = 0;

    public void packing(){
        massivePacking(0);
    }
    /**
     * It will running package for evey level capable to pack
     * @param int level - how tall the packing are
     */
    private void massivePacking(int level){
        //System.out.println("Searching level: "+level+" size: "+linkedReference.size());
        try {
            
            LinkedList linkedReference = references.get(level);

            if(linkedReference.size() > 3){
                int levelRef = level + 1;
                Kandle s = (Kandle) linkedReference.getHead();
                Kandle b = (Kandle) linkedReference.getTail();
                Kandle k = new KandlePackage(s,b,levelRef);
            
                // System.out.println("\nPacking");  
                // System.out.println("references.get("+level+").size("+references.get(level).size()+")");
                // System.out.println("levelRef: "+levelRef);
                // System.out.println("s: " + s.toString());
                // System.out.println("b: " + b.toString());
                // System.out.println("k: " + k.toString());

                log.packingKandle(levelRef,
                                  s.toString(),
                                  b.toString(),
                                  k.toString());

               if(references.size() < levelRef){
                    references.add(levelRef, new LinkedList());
                }
                
                packingReference(k, s, b);
                references.get(levelRef).add(k);
                linkedReference.clear();

                // System.out.println("references.get("+level+").size("+references.get(level).size()+")");
                // System.out.println("references.get("+levelRef+").size("+references.get(levelRef).size()+")\n");
                massivePacking(levelRef);
            } 
            
            return;
            
        }catch (Exception e){
            // e.printStackTrace();
            // System.out.println("Exceptioned");
            references.add(new LinkedList());
            log.referenceListGrowing(level+1);

                // flag++;
                // if(flag == 5)
                //     throw e;
            
            /*  Call himself with the new reference
             *  linkedlist created. Call passing 
             */// the same level that was called
            massivePacking(level);
        }
    }


    /**
     * Responsible to reorganize references, seeing that
     * a package holds the Kandles inside and keeps their
     * references for next and prev as theiy did.
     * @param <Kandle> pk - PacKage receiving refs
     * @param <Kandle> ip - Inicial Pointer to range
     * @param <Kandle> fp - Final Pointer to range
     * list to search the right sorted spot to
     * put the new value on;
     */
    private void packingReference(Kandle pk, Kandle ip, Kandle fp){
        if(first.compareTo(ip) == 0)
            first = pk;

        if(last.compareTo(fp) == 0)
            last = pk;

    	pk.prev = ip.prev;
    	ip.prev = pk;
    	pk.next = fp.next;
    	fp.next = pk;

    	if(pk.prev != null) pk.prev.next = pk;
    	if(pk.next != null) pk.next.prev = pk;
    }



    /**
     * Method to help handle references
     * @param <Kandle> nk - new Kandle to be attached before k
     * @param <Kandle> k - Kandle reference to attach
     */
    private void linkBefore(Kandle nk, Kandle k){
        if(nk == null || k == null)
            throw new NullPointerException(Messages.LINK_BEFORE_NULL.toString());
        if (nk.next != null) nk.next.prev = null;
        if (k.prev != null) k.prev.next = null;
        nk.next = k;
        k.prev = nk;
    }


    /**
     * Method to help handle references
     * @param <Kandle> nk - new Kandle to be attached after k
     * @param <Kandle> k - Kandle reference to attach
     */
    private void linkAfter(Kandle nk, Kandle k){
        if(nk == null || k == null){
            if(nk instanceof KandlePackage){
                KandlePackage n = (KandlePackage) nk;
                if(first == null || first.compareTo(n.smallestKandle()) == 0 ) {
                    first = nk;
                    return;
                }
            } 
            throw new NullPointerException(Messages.LINK_AFTER_NULL.toString());
        }
        if (nk.prev != null) nk.prev.next = null;
        if (k.next != null) k.next.prev = null;
        nk.prev = k;
        k.next = nk;
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



    /*
     *  DEBUGGING METHODS *!
     */

    public String getHead(){
        return first.toString();
    }

    public String getList(){
        Kandle k = (Kandle) references.get(0).get(0);
    //                ArrayList
    //                     \    LinkedList     Kandle inside 
    //                      \  inside array   /  Linked List
    //                       |      |        |
        return "" + k.level; //+ " " + ((Kandle) references.get(0).get(0).getLevel());
    }

    public int referenceSize(int i){
        return references.get(i).size();
    }

    public void printArrayOn(int i){
        LinkedList linkedReference = references.get(i);
        for(int x = 0; x < linkedReference.size(); x++){
            System.out.println(linkedReference.get(x).toString());
        }
    }
}
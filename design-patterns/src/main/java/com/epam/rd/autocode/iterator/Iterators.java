package com.epam.rd.autocode.iterator;

import java.util.Iterator;

class Iterators {

    public static Iterator<Integer> intArrayTwoTimesIterator(int[] array){
        return new Iterator<Integer>() {
			private int index = 0;
			@Override
			public Integer next() {
				if(hasNext()) {
					int nextValue = array[index / 2];
					index++;
					return nextValue;
				}
				throw new java.util.NoSuchElementException();
			}
			
			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return index < array.length * 2;
			}
		};
    }

    public static Iterator<Integer> intArrayThreeTimesIterator(int[] array) {
        return new Iterator<Integer>() {
			private int index = 0;
			@Override
			public Integer next() {
				if(hasNext()) {
					int nextValue = array[index / 3];
					index++;
					return nextValue;
				}
				throw new java.util.NoSuchElementException();
			}
			
			@Override
			public boolean hasNext() {
				return index < array.length * 3;
			}
		};
    }

    public static Iterator<Integer> intArrayFiveTimesIterator(int[] array) {
        return new Iterator<Integer>() {
			private int index = 0;
			@Override
			public Integer next() {
				if(hasNext()) {
					int nextValue = array[index / 5];
					index++;
					return nextValue;
				}
				throw new java.util.NoSuchElementException();
			}
			
			@Override
			public boolean hasNext() {
				return index < array.length * 5;
			}
		};
        }

    public static Iterable<String> table(String[] columns, int[] rows){
        return new Iterable<String>() {
			
			@Override
			public Iterator<String> iterator() {
				// TODO Auto-generated method stub
				return new Iterator<String>() {
					private int colIndex = 0;
					private int rowIndex = 0;
					@Override
					public String next() {
						if(hasNext()) {
							String cellValue = columns[colIndex] + rows[rowIndex];
							rowIndex++;
							if(rowIndex >= rows.length) {
								rowIndex = 0;
								colIndex++;
							}
							return cellValue;
						}
						throw new java.util.NoSuchElementException();
					}
					
					@Override
					public boolean hasNext() {
						// TODO Auto-generated method stub
						return colIndex < columns.length;
					}
				};
			}
		};
    }



}

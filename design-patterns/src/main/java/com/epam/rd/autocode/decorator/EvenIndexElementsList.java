package com.epam.rd.autocode.decorator;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
/*
 * В данной реализации создаётся класс EvenIndexElementsList,
 *  который реализует интерфейс List<String> и оборачивает существующий список строк (sourceList).
 *  Этот класс фильтрует элементы с чётными индексами.
 */
public class EvenIndexElementsList implements List<String>{

	private final List<String> sourceList;
	
	public EvenIndexElementsList(List<String> sourceList) {
		this.sourceList = sourceList;
	}

	@Override
	public int size() {
		// размер списка (size()) уменьшается вдвое, так как мы рассматриваем только элементы с чётными индексами.
		return (sourceList.size()+1)/2;
	}

	@Override
	public boolean isEmpty() {
		return sourceList.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return sourceList.indexOf(o) % 2 == 0;
	}

	@Override
	public Iterator<String> iterator() {
		// возвращаем итератор, который будет работать с элементами, 
		// находящимися на четных индексах исходного списка sourceList.
		return new Iterator<String>() {
			private int currentIndex = 0;
			
			@Override
			public String next() {				
				/*
				 * В методе next(), мы возвращаем следующий элемент из декорированного списка с использованием метода get(currentIndex)
				 *  и увеличиваем currentIndex на 1, чтобы перейти к следующему элементу.
				 */				
				if (!hasNext()) {
	                throw new NoSuchElementException();
	            }
				
				String nextElement = get(currentIndex);
				currentIndex++;
				return nextElement;
			}
			
			@Override
			public boolean hasNext() {
				// В методе hasNext(), мы проверяем, есть ли следующий элемент, 
				//сравнивая currentIndex с размером декорированного списка (size()).
				return currentIndex < size(); // Проверяем, есть ли следующий элемент
			}
		};
	}

	@Override
	public Object[] toArray() {
		//Этот метод создает новый массив, затем заполняет его элементами из исходного списка с четными индексами.
		Object[] result = new Object[size()];
	    int index = 0;
	    for (int i = 0; i < sourceList.size(); i += 2) {
	        result[index++] = sourceList.get(i);
	    }
	    return result;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		 if (a.length < size()) {
		        // Если массив a недостаточно большой, создаем новый массив нужного размера.
		        @SuppressWarnings("unchecked")
		        T[] result = (T[]) java.lang.reflect.Array
		                .newInstance(a.getClass().getComponentType(), size());
		        a = result;
		    }
		    for (int i = 0; i < size(); i++) {
		        a[i] = (T) get(i);
		    }
		    return a;
	}

	@Override
	public boolean add(String e) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("add is not supported");
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("remove is not supported");
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		for (Object item : c) {
	        if (!contains(item)) {
	            return false;
	        }
	    }
	    return true;
	}

	@Override
	public boolean addAll(Collection<? extends String> c) {
		throw new UnsupportedOperationException("addAll is not supported");
	}

	@Override
	public boolean addAll(int index, Collection<? extends String> c) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("addAll is not supported");
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException("removeAll is not supported");
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		 throw new UnsupportedOperationException("retainAll is not supported");
	}

	@Override
	public void clear() {
		sourceList.clear();		
	}

	@Override
	public String get(int index) {
		if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        }
        return sourceList.get(index * 2); // возвращает элементы с чётными индексами из исходного списка.
	}

	@Override
	public String set(int index, String element) {
		throw new UnsupportedOperationException("set is not supported");
	}

	@Override
	public void add(int index, String element) {
		throw new UnsupportedOperationException("add is not supported");
		
	}

	@Override
	public String remove(int index) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("remove is not supported");
	}

	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		 throw new UnsupportedOperationException("remove is not supported");
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("lastIndexOf is not supported");
	}

	@Override
	public ListIterator<String> listIterator() {
		// TODO Auto-generated method stub
		return listIterator(0);
	}

	@Override
	public ListIterator<String> listIterator(int index) {
		// TODO Auto-generated method stub
		return new ListIterator<String>() {
			private int currentIndex = index;

			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				 return currentIndex < size();
			}

			@Override
			public String next() {
				if (!hasNext()) {
	                throw new NoSuchElementException();
	            }
	            String nextElement = get(currentIndex);
	            currentIndex++;
	            return nextElement;
			}

			@Override
			public boolean hasPrevious() {
				 return currentIndex > 0;
			}

			@Override
			public String previous() {
				if (!hasPrevious()) {
	                throw new NoSuchElementException();
	            }
	            currentIndex--;
	            return get(currentIndex);
			}

			@Override
			public int nextIndex() {
				// TODO Auto-generated method stub
				return currentIndex;
			}

			@Override
			public int previousIndex() {
				// TODO Auto-generated method stub
				return currentIndex - 1;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException("remove is not supported");
				
			}

			@Override
			public void set(String e) {
				throw new UnsupportedOperationException("set is not supported");
				
			}

			@Override
			public void add(String e) {
				throw new UnsupportedOperationException("add is not supported");
				
			}
		
			
		};
	}

	@Override
	public List<String> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("subList is not supported");
	}

}

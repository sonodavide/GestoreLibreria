package com.sonodavide.gestorelibreria.service.BookDtoOrderStrategy;

import com.sonodavide.gestorelibreria.model.BookDto;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class AuthorsBookDtoOrderStategy implements BookDtoOrderStrategy {
    @Override
    public List<BookDto> execute(List<BookDto> bookDtos, boolean reverse) {
        Comparator<BookDto> comparator = new AuthorsComparator();
        if (reverse){
            comparator = comparator.reversed();
        }
        return bookDtos.stream().sorted(comparator).collect(Collectors.toList());
    }

    class AuthorsComparator implements Comparator<BookDto> {

        @Override
        public int compare(BookDto b1, BookDto b2) {
            return compareSortedStrings(b1.getAuthors(), b2.getAuthors());
        }

        private int compareSortedStrings(List<String> s1, List<String> s2) {
            if (s1 == s2) return 0;
            if (s1 == null) return -1;
            if (s2 == null) return 1;

            Iterator<String> it1 = s1.iterator();
            Iterator<String> it2 = s2.iterator();

            while (it1.hasNext() && it2.hasNext()) {
                int cmp = it1.next().compareToIgnoreCase(it2.next());
                if (cmp != 0) return cmp;
            }

            return Integer.compare(
                    s1.size(), s2.size()
            );
        }
    }
}

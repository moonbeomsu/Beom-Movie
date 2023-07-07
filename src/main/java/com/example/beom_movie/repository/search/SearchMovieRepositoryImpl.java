package com.example.beom_movie.repository.search;

import com.example.beom_movie.entity.Movie;
import com.example.beom_movie.entity.QMovie;
import com.example.beom_movie.entity.QMovieImage;
import com.example.beom_movie.entity.QReview;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;


import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class SearchMovieRepositoryImpl extends QuerydslRepositorySupport implements SearchMovieRepository {

    public SearchMovieRepositoryImpl() {
        super(Movie.class);
    }

    @Override
    public Movie search1() {

        log.info("search1......................");

        QMovie movie = QMovie.movie;

        JPQLQuery<Movie> jpqlQuery = from(movie);

        jpqlQuery.select(movie).where(movie.mno.eq(10L));

        log.info("-------------------------------");
        log.info(jpqlQuery);
        log.info("-------------------------------");

        List<Movie> result = jpqlQuery.fetch();


        return null;

    }

    @Override
    public Page<Object[]> searchPage(String type, String keyword, Pageable pageable) {

        log.info("searchPage....................");

        QMovie movie = QMovie.movie;
        QReview review = QReview.review;
        QMovieImage movieImage = QMovieImage.movieImage;

        JPQLQuery<Movie> jpqlQuery = from(movie);
        jpqlQuery.leftJoin(movieImage).on(movie.eq(movieImage.movie));
        jpqlQuery.leftJoin(review).on(movie.eq(review.movie));

        JPQLQuery<Tuple> tuple = jpqlQuery.select(movie, movieImage,review.grade.avg(),review.count());

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        BooleanExpression expression = movie.mno.gt(0L);

        booleanBuilder.and(expression);

        if (type != null) {
            String types = type;

            BooleanBuilder conditionBuilder = new BooleanBuilder();


            if (types.equals("t")) {
                conditionBuilder.or(movie.title.contains(keyword));
            }

            booleanBuilder.and(conditionBuilder);

        }

        tuple.where(booleanBuilder);


        Sort sort = pageable.getSort();

        sort.stream().forEach(order -> {
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;
            String prop = order.getProperty();

            PathBuilder orderByExpression = new PathBuilder(Movie.class, "movie");
            tuple.orderBy(new OrderSpecifier(direction, orderByExpression.get(prop)));

        });
        tuple.groupBy(movie);

        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());


        List<Tuple> result = tuple.fetch();

        log.info(result);

        long count = tuple.fetchCount();
        log.info("count : " + count);

        return new PageImpl<Object[]>(
                result.stream().map(t -> t.toArray()).collect(Collectors.toList()), pageable, count);


    }
}

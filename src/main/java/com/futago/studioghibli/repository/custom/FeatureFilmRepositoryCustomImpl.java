package com.futago.studioghibli.repository.custom;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.futago.studioghibli.entity.FeatureFilm;
import com.futago.studioghibli.entity.FeatureFilm_;
import com.futago.studioghibli.entity.Titles;
import com.futago.studioghibli.entity.Titles_;
import com.futago.studioghibli.rest.filter.FeatureFilmFilter;

public class FeatureFilmRepositoryCustomImpl implements FeatureFilmRepositoryCustom {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<FeatureFilm> findByFilter(FeatureFilmFilter filter) {
		// The CriteriaBuilder interface serves as the main factory of criteria queries
		// and criteria query elements
		CriteriaBuilder cb = em.getCriteriaBuilder();

		// The CriteriaQuery interface defines functionality that is specific to
		// top-level queries
		CriteriaQuery<FeatureFilm> criteriaQuery = cb.createQuery(FeatureFilm.class);

		// A root type in the from clause. Query roots always reference entities
		Root<FeatureFilm> root = criteriaQuery.from(FeatureFilm.class);

		// The type of a simple or compound predicate
		List<Predicate> predicates = getPredicates(cb, root, filter);

		// The select and where clauses
		criteriaQuery.select(root).where(predicates.toArray(new Predicate[predicates.size()]));

		// The order by clause
		criteriaQuery.orderBy(cb.asc(root.get(FeatureFilm_.id)));

		// Create an instance of TypedQuery for executing a criteria query
		TypedQuery<FeatureFilm> query = em.createQuery(criteriaQuery);

		return query.setMaxResults(100).getResultList();
	}

	private List<Predicate> getPredicates(CriteriaBuilder cb, Root<FeatureFilm> root, FeatureFilmFilter filter) {
		List<Predicate> predicates = new ArrayList<>();

		if (filter != null) {
			if (filter.getTitle() != null) {
				predicates.add(getTitlePredicate(cb, root, filter));
			}

			if (filter.getYear() != null) {
				predicates.add(getYearPredicate(cb, root, filter));
			}

			if (filter.getDirector() != null) {
				predicates.add(getDirectorPredicate(cb, root, filter));
			}

			if (filter.getScreenwriter() != null) {
				predicates.add(getScreenwriterPredicate(cb, root, filter));
			}

			if (filter.getProducer() != null) {
				predicates.add(getProducerPredicate(cb, root, filter));
			}

			if (filter.getMusic() != null) {
				predicates.add(getMusicPredicate(cb, root, filter));
			}
		}

		return predicates;
	}

	private Predicate getTitlePredicate(CriteriaBuilder cb, Root<FeatureFilm> root, FeatureFilmFilter filter) {
		// Create an inner join
		Join<FeatureFilm, Titles> titles = root.join(FeatureFilm_.titles);

		String titleLike = "%" + filter.getTitle().toUpperCase() + "%";

		Predicate japaneseTitlePredicate = cb.like(cb.upper(titles.get(Titles_.japanese)), titleLike);
		Predicate hepburnTitlePredicate = cb.like(cb.upper(titles.get(Titles_.hepburn)), titleLike);
		Predicate englishTitlePredicate = cb.like(cb.upper(titles.get(Titles_.english)), titleLike);
		Predicate portugueseTitlePredicate = cb.like(cb.upper(titles.get(Titles_.portuguese)), titleLike);

		// Create a disjunction of the given restriction predicates
		return cb.or(japaneseTitlePredicate, hepburnTitlePredicate, englishTitlePredicate, portugueseTitlePredicate);
	}

	private Predicate getYearPredicate(CriteriaBuilder cb, Root<FeatureFilm> root, FeatureFilmFilter filter) {
		Predicate yearPredicate = cb.equal(root.get(FeatureFilm_.year), filter.getYear());

		return cb.and(yearPredicate);
	}

	private Predicate getDirectorPredicate(CriteriaBuilder cb, Root<FeatureFilm> root, FeatureFilmFilter filter) {
		String directorLike = "%" + filter.getDirector().toUpperCase() + "%";

		Predicate directorPredicate = cb.like(cb.upper(root.get(FeatureFilm_.director)), directorLike);

		return cb.and(directorPredicate);
	}

	private Predicate getScreenwriterPredicate(CriteriaBuilder cb, Root<FeatureFilm> root, FeatureFilmFilter filter) {
		String screenwriterLike = "%" + filter.getScreenwriter().toUpperCase() + "%";

		Predicate screenwriterPredicate = cb.like(cb.upper(root.get(FeatureFilm_.screenwriter)), screenwriterLike);

		return cb.and(screenwriterPredicate);
	}

	private Predicate getProducerPredicate(CriteriaBuilder cb, Root<FeatureFilm> root, FeatureFilmFilter filter) {
		String producerLike = "%" + filter.getProducer().toUpperCase() + "%";

		Predicate producerPredicate = cb.like(cb.upper(root.get(FeatureFilm_.producer)), producerLike);

		return cb.and(producerPredicate);
	}

	private Predicate getMusicPredicate(CriteriaBuilder cb, Root<FeatureFilm> root, FeatureFilmFilter filter) {
		String musicLike = "%" + filter.getMusic().toUpperCase() + "%";

		Predicate musicPredicate = cb.like(cb.upper(root.get(FeatureFilm_.music)), musicLike);

		return cb.and(musicPredicate);
	}
}
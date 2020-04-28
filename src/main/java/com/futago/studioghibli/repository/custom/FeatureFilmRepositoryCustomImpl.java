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
		Root<FeatureFilm> featureFilmRoot = criteriaQuery.from(FeatureFilm.class);

		// The type of a simple or compound predicate
		List<Predicate> predicates = getPredicates(cb, featureFilmRoot, filter);

		// The select and where clauses
		criteriaQuery.select(featureFilmRoot).where(predicates.toArray(new Predicate[predicates.size()]));

		// The order by clause
		criteriaQuery.orderBy(cb.asc(featureFilmRoot.get(FeatureFilm_.id)));

		// Create an instance of TypedQuery for executing a criteria query
		TypedQuery<FeatureFilm> query = em.createQuery(criteriaQuery);

		return query.setMaxResults(100).getResultList();
	}

	private List<Predicate> getPredicates(CriteriaBuilder cb, Root<FeatureFilm> featureFilmRoot,
			FeatureFilmFilter filter) {

		List<Predicate> predicates = new ArrayList<>();

		if (filter != null) {
			if (filter.getDirector() != null) {
				String directorLike = "%" + filter.getDirector().toUpperCase() + "%";

				Predicate directorPredicate = cb.like(cb.upper(featureFilmRoot.get(FeatureFilm_.director)),
						directorLike);

				predicates.add(cb.and(directorPredicate));
			}

			if (filter.getYear() != null) {
				Predicate yearPredicate = cb.equal(featureFilmRoot.get(FeatureFilm_.year), filter.getYear());

				predicates.add(cb.and(yearPredicate));
			}

			if (filter.getTitle() != null) {
				// Create an inner join
				Join<FeatureFilm, Titles> titles = featureFilmRoot.join(FeatureFilm_.titles);

				String titleLike = "%" + filter.getTitle().toUpperCase() + "%";

				Predicate japaneseTitlePredicate = cb.like(cb.upper(titles.get(Titles_.japanese)), titleLike);
				Predicate hepburnTitlePredicate = cb.like(cb.upper(titles.get(Titles_.hepburn)), titleLike);
				Predicate englishTitlePredicate = cb.like(cb.upper(titles.get(Titles_.english)), titleLike);
				Predicate portugueseTitlePredicate = cb.like(cb.upper(titles.get(Titles_.portuguese)), titleLike);

				// Create a disjunction of the given restriction predicates
				predicates.add(cb.or(japaneseTitlePredicate, hepburnTitlePredicate, englishTitlePredicate,
						portugueseTitlePredicate));
			}
		}

		return predicates;
	}
}
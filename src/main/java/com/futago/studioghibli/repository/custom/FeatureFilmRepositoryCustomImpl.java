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
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<FeatureFilm> criteriaQuery = cb.createQuery(FeatureFilm.class);
		Root<FeatureFilm> featureFilmRoot = criteriaQuery.from(FeatureFilm.class);

		List<Predicate> predicates = getPredicates(cb, featureFilmRoot, filter);
		criteriaQuery.select(featureFilmRoot).where(predicates.toArray(new Predicate[predicates.size()]));
		criteriaQuery.orderBy(cb.asc(featureFilmRoot.get(FeatureFilm_.id)));

		TypedQuery<FeatureFilm> query = em.createQuery(criteriaQuery);
		return query.setMaxResults(100).getResultList();
	}

	private List<Predicate> getPredicates(CriteriaBuilder cb, Root<FeatureFilm> featureFilmRoot,
			FeatureFilmFilter filter) {
		List<Predicate> predicates = new ArrayList<>();

		if (filter != null) {
			if (filter.getDirector() != null) {
				String directorLike = "%" + filter.getDirector().toUpperCase() + "%";
				
				Predicate directorPredicate = cb.like(cb.upper(featureFilmRoot.get(FeatureFilm_.director)), directorLike);
				
				predicates.add(cb.and(directorPredicate));
			}

			if (filter.getYear() != null) {
				Predicate yearPredicate = cb.equal(featureFilmRoot.get(FeatureFilm_.year), filter.getYear());
				
				predicates.add(cb.and(yearPredicate));
			}

			if (filter.getTitle() != null) {
				Join<FeatureFilm, Titles> titles = featureFilmRoot.join(FeatureFilm_.titles);

				String titleLike = "%" + filter.getTitle().toUpperCase() + "%";

				Predicate japaneseTitlePredicate = cb.like(cb.upper(titles.get(Titles_.japanese)), titleLike);
				Predicate hepburnTitlePredicate = cb.like(cb.upper(titles.get(Titles_.hepburn)), titleLike);
				Predicate englishTitlePredicate = cb.like(cb.upper(titles.get(Titles_.english)), titleLike);
				Predicate portugueseTitlePredicate = cb.like(cb.upper(titles.get(Titles_.portuguese)), titleLike);

				predicates.add(cb.or(japaneseTitlePredicate, hepburnTitlePredicate, englishTitlePredicate, portugueseTitlePredicate));
			}
		}

		return predicates;
	}
}
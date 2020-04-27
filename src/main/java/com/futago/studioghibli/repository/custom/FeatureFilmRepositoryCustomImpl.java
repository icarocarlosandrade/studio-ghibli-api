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
import com.futago.studioghibli.entity.Titles;
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
		criteriaQuery.orderBy(cb.asc(featureFilmRoot.get("id")));

		TypedQuery<FeatureFilm> query = em.createQuery(criteriaQuery);
		return query.setMaxResults(100).getResultList();
	}

	private List<Predicate> getPredicates(CriteriaBuilder cb, Root<FeatureFilm> featureFilmRoot,
			FeatureFilmFilter filter) {
		List<Predicate> predicates = new ArrayList<>();

		if (filter != null) {
			if (filter.getDirector() != null) {
				predicates.add(cb.and(cb.equal(featureFilmRoot.get("director"), filter.getDirector())));
			}

			if (filter.getYear() != null) {
				predicates.add(cb.and(cb.equal(featureFilmRoot.get("year"), filter.getYear())));
			}

			if (filter.getTitle() != null) {
				Join<FeatureFilm, Titles> titles = featureFilmRoot.join("titles");

				String title = "%" + filter.getTitle().toUpperCase() + "%";

				Predicate predicateJapanese = cb.like(cb.upper(titles.get("japanese")), title);
				Predicate predicateHepburn = cb.like(cb.upper(titles.get("hepburn")), title);
				Predicate predicateEnglish = cb.like(cb.upper(titles.get("english")), title);
				Predicate predicatePortuguese = cb.like(cb.upper(titles.get("portuguese")), title);

				predicates.add(cb.or(predicateJapanese, predicateHepburn, predicateEnglish, predicatePortuguese));
			}
		}

		return predicates;
	}
}
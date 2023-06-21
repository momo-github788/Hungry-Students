package za.ac.cput.repository.interfaces;

import java.util.List;

import za.ac.cput.domain.PointsHistory;

public interface IPointsHistoryRepository {
    List<PointsHistory> getAll();
}

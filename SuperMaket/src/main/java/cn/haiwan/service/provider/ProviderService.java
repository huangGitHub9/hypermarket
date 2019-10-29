package cn.haiwan.service.provider;

import java.util.List;

import cn.haiwan.entity.Provider;


public interface ProviderService {
    List<Provider> findProvider();

    int addProvider(Provider provider);

    Provider findById(Integer providerId);

    int modifyProvider(Provider provider);

    /*List<Provider> checkIspayment(Integer id);*/

    int delProvider(Integer providerId);

    int findRecordCount(String proCode, String proName);

    List<Provider> findProviderBy(String proCode, String proName, int pageIndex, int pageSize);
}

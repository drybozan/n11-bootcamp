package com.n11.n11bootcamp.mapper;
//https://mapstruct.org/documentation/stable/reference/html/     daha fazla detay bu linkte

import com.n11.n11bootcamp.dto.AccountDTO;
import com.n11.n11bootcamp.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE) //hedefte mapleme yapamadığı bir field oldugunda ignore et
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    /*
mapstruct yapısında,karşılıklı maplenecek olan dto ve entity field leri aynı isimde ise otomatik mapleme yapılır ,
eğer kaynak ile hedef arasındaki field adları farklı ise bunu belirtlememiz lazım bu şekilde :

 @Mapping(target = "name", source = "nameXXX")
*/
    AccountDTO convertToAccountDTO(Account account);

    List<AccountDTO> convertToAccountDTOs(List<Account> accounts);
}

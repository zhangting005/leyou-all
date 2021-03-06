package com.leyou.item.api;

import com.leyou.item.pojo.Brand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface BrandApi {
    // 根据品牌id查询品牌
    @GetMapping("brand/{id}")
    Brand queryBrandById(@PathVariable("id")Long id);

    // 根据bid的集合查询品牌信息
    @GetMapping("brand/list")
    List<Brand> queryBrandsByIds(@RequestParam("ids") List<Long> ids);
}

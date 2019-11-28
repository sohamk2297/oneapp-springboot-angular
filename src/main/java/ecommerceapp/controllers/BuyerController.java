package ecommerceapp.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ecommerceapp.models.Buyer;
import ecommerceapp.models.Cart;
import ecommerceapp.models.CartItem;
import ecommerceapp.models.Product;
import ecommerceapp.repos.BuyerRepository;
import ecommerceapp.repos.ProductRepository;
import ecommerceapp.repos.UserRepository;

@RestController
@RequestMapping("/buyer")
public class BuyerController {


	@Autowired
	private BuyerRepository buyerRepository;

	@Autowired
	private ProductRepository productRepository;

	@PostMapping(path = "/add" ,consumes = "application/json")
	public @ResponseBody Buyer addBuyer(@RequestBody Buyer buyer) {
		//		buyer.setEncryptedPassword(buyer.getEncryptedPassword());
		return buyerRepository.save(buyer);
	}

	@PostMapping(path = "/buy/uname/{uname}/pid/{productid}/qty/{quantity}")
	public @ResponseBody Cart addToCart(@PathVariable(name = "uname") String uname,@PathVariable(name = "productid") Integer productid,@PathVariable(name = "quantity") Integer quantity)
	{
		Optional<Buyer> optional = buyerRepository.findById(uname);
		if ( !optional.isEmpty())
		{
			Buyer buyer = optional.get();

			if(!productRepository.findById(productid).isEmpty())
			{
				Product product = productRepository.findById(productid).get();
				/*
				 * product.setStock(product.getStock() - quantity);
				 * productRepository.save(product);
				 */
				if(buyer.getCart()==null)
				{
					Cart cart = new Cart();
					Collection<CartItem> cartItems = new ArrayList<CartItem>();
					CartItem cartItem = new CartItem();
					cartItem.setProduct(product);
					cartItem.setQuantity(quantity);
					cartItems.add(cartItem);
					cart.setCartItems(cartItems);
					cart.setTotalPrice(product.getPrice()*quantity);
					buyer.setCart(cart);
					buyerRepository.save(buyer);
					return cart;

				}
				else
				{
					Cart cart = buyer.getCart();
					Collection<CartItem> cartItems = cart.getCartItems();
					CartItem cartItem = new CartItem();
					cartItem.setProduct(product);
					cartItem.setQuantity(quantity);
					cartItems.add(cartItem);
					cart.setCartItems(cartItems);
					cart.setTotalPrice(cart.getTotalPrice()+ product.getPrice()*quantity);
					buyer.setCart(cart);
					buyerRepository.save(buyer);
					return cart;
				}
			}
			else
			{
				return null;
			}
		}
		else
		{
			return null;
		}

	}

}
